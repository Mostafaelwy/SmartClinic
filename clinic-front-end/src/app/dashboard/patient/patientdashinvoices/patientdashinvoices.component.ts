import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashinvoices',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashinvoices.component.html',
    styleUrl: './patientdashinvoices.component.scss'
})
export class PatientdashinvoicesComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 