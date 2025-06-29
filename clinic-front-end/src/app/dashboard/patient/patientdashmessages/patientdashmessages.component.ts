import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashmessages',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashmessages.component.html',
    styleUrl: './patientdashmessages.component.scss'
})
export class PatientdashmessagesComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 