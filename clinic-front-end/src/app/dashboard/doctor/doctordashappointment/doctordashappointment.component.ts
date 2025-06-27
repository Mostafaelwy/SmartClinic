import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/sharedModule';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { PagingFilter, ReservationFilter, ReservationItem, ReservationStatus } from '../../../../types';
const DEFAULT_DATERANGEPICKER_CONFIG = {
  separator: ' - ',
  format: 'MM/DD/YYYY',
  applyLabel: 'Apply',
  cancelLabel: 'Cancel',
  clearLabel: 'Clear'
};
@Component({
  selector: 'app-doctordashappointment',
  standalone: true,

  imports: [
    CommonModule,
    FormsModule,
    SharedModule
  ],
  templateUrl: './doctordashappointment.component.html',
  styleUrl: './doctordashappointment.component.scss'
})
export class DoctordashappointmentComponent {
  ReservationStatus = ReservationStatus; // expose enum to HTML
  selectedStatus:ReservationStatus = ReservationStatus.ACCEPTED
  _reservationFilter: ReservationFilter = {
      status:ReservationStatus.PENDING

    };
  private _pagingFilter: PagingFilter = {
    pageSize:8
  };
  reservationItems:ReservationItem[] = [];
  activeTab: ReservationStatus = ReservationStatus.ACCEPTED;

  constructor(private doctorService:DoctorService){
  }
  selected: { startDate:Date, endDate:Date }=  {
    startDate:new Date(), endDate:new Date()
  };

  ngOnInit(){
    this.initializeLast7Days();
  }

  initializeLast7Days(): void {
    const today = new Date();
    const sevenDaysAgo = new Date();
    sevenDaysAgo.setDate(today.getDate() - 6); // 6 days before today to include today (7 days total)

    this.selected = {
      startDate: sevenDaysAgo,
      endDate: today
    };
  }

  private loadReservations(): void {
    this.doctorService.getReservations(this._reservationFilter, this._pagingFilter).subscribe({
      next: (res) => this.reservationItems = res.content,
      error: (err) => console.error('❌ Error loading reservations:', err)
    });
  }

  onDateRangeChange(event: { startDate: Date; endDate: Date }){
    this._reservationFilter.startTime = event.startDate.toISOString().slice(0, 10);
    this._reservationFilter.endTime = event.endDate.toISOString().slice(0, 10);
    this.loadReservations();
  }

  onTabChange(status: ReservationStatus) {
    console.log("tab changed")
  this.selectedStatus = status;
  this._reservationFilter.status = status
  this.loadReservations();
  // this.updateSearchFilterBasedOnTab(tab);
}

}
