import { Component, OnInit } from '@angular/core';
import { DateRangeEnum, PagingFilter, ReservationFilter, ReservationItem, ReservationStatus } from '../../../../types';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-doctordashrequests',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './doctordashrequests.component.html',
  styleUrl: './doctordashrequests.component.scss'
})
export class DoctordashrequestsComponent implements OnInit{


  constructor(private doctorService:DoctorService){

  }
  ngOnInit(): void {
    this.updateDateRange(this.selectedRange)
    this.loadReservations();
  }
  private _reservationFilter: ReservationFilter = {
    status:ReservationStatus.PENDING

  };
    private _pagingFilter: PagingFilter = {

    };
    reservationItems:ReservationItem[] = [];
    DateRangeEnum = DateRangeEnum; // to access enum in template
    dateRangeOptions = Object.values(DateRangeEnum);
    selectedRange: DateRangeEnum = DateRangeEnum.LAST_7_DAYS;
    startTime?: string;
    endTime?: string;


    private loadReservations(): void {
    this.doctorService.getReservations(this._reservationFilter, this._pagingFilter).subscribe({
      next: (res) => this.reservationItems = res.content,
      error: (err) => console.error('❌ Error loading reservations:', err)
    });
  }

    updateDateRange(option: DateRangeEnum): void {
    const now = new Date();
    let start: Date, end: Date = new Date(now);

    switch (option) {
      case DateRangeEnum.TODAY:
        start = new Date(now.getFullYear(), now.getMonth(), now.getDate());
        break;

      case DateRangeEnum.LAST_7_DAYS:
        start = new Date();
        start.setDate(now.getDate() - 6);
        start.setHours(0, 0, 0, 0);
        break;

      case DateRangeEnum.THIS_MONTH:
        start = new Date(now.getFullYear(), now.getMonth(), 1);
        break;

      default:
        start = new Date(now);
        break;
    }

  this._reservationFilter.startTime = start.toISOString().split('T')[0]; // '2025-06-20'
  this._reservationFilter.endTime = end.toISOString().split('T')[0];     // '2025-06-26'
    this.loadReservations();
  }

  onAccept(id: number): void {
    this.doctorService.acceptReservation(id).subscribe({
      next: () => this.loadReservations(), // reload the list
      error: err => console.error('Accept failed', err)
    });
  }

  onReject(id: number): void {
    this.doctorService.rejectReservation(id).subscribe({
      next: () => this.loadReservations(),
      error: err => console.error('Reject failed', err)
    });
  }
  onLoadMore() {
    const currSize = this._pagingFilter.pageSize
    this._pagingFilter = {
      pageSize: currSize? currSize + 5: undefined
    }
    this.loadReservations();
  }
}
