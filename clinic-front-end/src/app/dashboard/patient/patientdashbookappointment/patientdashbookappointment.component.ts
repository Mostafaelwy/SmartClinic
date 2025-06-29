import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashbookappointment',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashbookappointment.component.html',
    styleUrl: './patientdashbookappointment.component.scss'
})
export class PatientdashbookappointmentComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 