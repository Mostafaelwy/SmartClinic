import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashvitals',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashvitals.component.html',
    styleUrl: './patientdashvitals.component.scss'
})
export class PatientdashvitalsComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 