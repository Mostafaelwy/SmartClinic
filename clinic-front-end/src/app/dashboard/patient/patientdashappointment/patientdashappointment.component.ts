import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashappointment',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashappointment.component.html',
    styleUrl: './patientdashappointment.component.scss'
})
export class PatientdashappointmentComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 