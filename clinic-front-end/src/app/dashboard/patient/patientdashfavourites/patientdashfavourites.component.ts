import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-patientdashfavourites',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashfavourites.component.html',
    styleUrl: './patientdashfavourites.component.scss'
})
export class PatientdashfavouritesComponent implements OnInit {
    loading: boolean = true;

    constructor() {

    }

    ngOnInit(): void {

    }

} 