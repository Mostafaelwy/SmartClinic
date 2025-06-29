import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashwallet',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashwallet.component.html',
    styleUrl: './patientdashwallet.component.scss'
})
export class PatientdashwalletComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 