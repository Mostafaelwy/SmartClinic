import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashprofile',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashprofile.component.html',
    styleUrl: './patientdashprofile.component.scss'
})
export class PatientdashprofileComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 