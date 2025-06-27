import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { DateRangeEnum, PagingFilter, ReservationFilter, ReservationItem } from '../../../../../types';
import { DoctorService } from '../../../../services/doctor/doctor.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment-list-mini',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './appointment-list-mini.component.html',
  styleUrl: './appointment-list-mini.component.scss'
})
export class AppointmentListMiniComponent {
  private _reservationFilter: ReservationFilter = {};
  private _pagingFilter: PagingFilter = {};
  reservationItems:ReservationItem[] = [];
  DateRangeEnum = DateRangeEnum; // to access enum in template
  dateRangeOptions = Object.values(DateRangeEnum);
  selectedRange: DateRangeEnum = DateRangeEnum.LAST_7_DAYS;
  startTime?: string;
  endTime?: string;


  @Input()
  set reservationFilter(value: ReservationFilter) {
    this._reservationFilter = value;
    this.updateDateRange(this.selectedRange)
    this.loadReservations(); // re-fetch on any change
  }

  @Input()
  set pagingFilter(value: PagingFilter) {
    this._pagingFilter = value;
    this.updateDateRange(this.selectedRange)
    this.loadReservations(); // re-fetch on any change
  }


  constructor(private doctorService:DoctorService){
  }

  private loadReservations(): void {
    this.doctorService.getReservations(this._reservationFilter, this._pagingFilter).subscribe({
      next: (res) => this.reservationItems = res.content,
      error: (err) => console.error('❌ Error loading reservations:', err)
    });
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

}
