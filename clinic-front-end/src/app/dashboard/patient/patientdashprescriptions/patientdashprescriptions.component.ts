import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashprescriptions',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashprescriptions.component.html',
    styleUrl: './patientdashprescriptions.component.scss'
})
export class PatientdashprescriptionsComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 