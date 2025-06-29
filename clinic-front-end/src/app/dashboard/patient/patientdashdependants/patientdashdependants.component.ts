import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashdependants',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashdependants.component.html',
    styleUrl: './patientdashdependants.component.scss'
})
export class PatientdashdependantsComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 