import { CommonModule } from '@angular/common';
import { Component, Pipe, PipeTransform } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Pipe({ name: 'jsonToEntries', standalone: true })
export class JsonToEntriesPipe implements PipeTransform {
  transform(value: string): { key: string, value: any }[] {
    try {
      const obj = JSON.parse(value);
      if (obj && obj.model_predictions) {
        return Object.entries(obj.model_predictions).map(([key, value]) => ({ key, value }));
      }
      return [];
    } catch {
      return [];
    }
  }
}

@Component({
  selector: 'app-ai-predictor',
  standalone: true,
  imports: [FormsModule, CommonModule, JsonToEntriesPipe],
  templateUrl: './ai-predictor.component.html',
  styleUrls: ['./ai-predictor.component.scss']
})
export class AiPredictorComponent {
  activeTab: 'settings' | 'predict' = 'predict';
  endpointUrl: string = localStorage.getItem('predictor_endpoint') || '';
  smiles: string = '';
  predictionResult: string = '';
  resultClass: string = '';
  detailsLinkVisible: boolean = false;
  error: string = '';

  get canonicalSmiles(): string | null {
    return localStorage.getItem('canonical_smiles');
  }
  get modelResults(): string | null {
    return localStorage.getItem('model_results');
  }

  switchTab(tab: 'settings' | 'predict') {
    this.activeTab = tab;
  }

  saveEndpoint() {
    localStorage.setItem('predictor_endpoint', this.endpointUrl.trim());
  }

  predict() {
    this.error = '';
    this.predictionResult = '';
    this.resultClass = '';
    this.detailsLinkVisible = false;
    const endpoint = localStorage.getItem('predictor_endpoint');
    if (!endpoint) {
      this.error = 'Please set an URL for the predictor in local storage (predictor_endpoint).';
      return;
    }
    if (!this.smiles.trim()) {
      this.error = 'Please enter a SMILES string.';
      return;
    }
    fetch(endpoint, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ smiles: this.smiles.trim() })
    })
      .then(res => res.json())
      .then(data => {
        if (data.majority_vote === 1) {
          this.predictionResult = '✅ Drug likely passes the BBB';
          this.resultClass = 'pass';
        } else if (data.majority_vote === 0) {
          this.predictionResult = '❌ Drug unlikely to pass the BBB';
          this.resultClass = 'fail';
        } else {
          this.predictionResult = 'Prediction error.';
          this.resultClass = '';
        }
        localStorage.setItem('smiles', this.smiles.trim());
        localStorage.setItem('canonical_smiles', data.canonical_smiles);
        localStorage.setItem('majority_vote', data.majority_vote);
        if (data.model_predictions) {
          localStorage.setItem('model_results', JSON.stringify({ model_predictions: data.model_predictions }));
        } else {
          localStorage.removeItem('model_results');
        }
        this.detailsLinkVisible = true;
      })
      .catch(err => {
        this.error = 'Prediction failed. Is your predictor server running?';
      });
  }
} 