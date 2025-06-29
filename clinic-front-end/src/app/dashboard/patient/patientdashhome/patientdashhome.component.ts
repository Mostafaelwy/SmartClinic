import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashhome',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashhome.component.html',
    styleUrl: './patientdashhome.component.scss'
})
export class PatientdashhomeComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 