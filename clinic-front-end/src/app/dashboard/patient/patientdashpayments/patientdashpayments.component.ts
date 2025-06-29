import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashpayments',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashpayments.component.html',
    styleUrl: './patientdashpayments.component.scss'
})
export class PatientdashpaymentsComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 