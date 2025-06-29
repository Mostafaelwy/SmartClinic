import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashmedicalrecords',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashmedicalrecords.component.html',
    styleUrl: './patientdashmedicalrecords.component.scss'
})
export class PatientdashmedicalrecordsComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 