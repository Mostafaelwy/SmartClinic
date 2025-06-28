import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Clinic, DayOfWeek, DaySlot, SlotDTO, SlotFormModel, SlotModel, TimeObject } from '../../../../types';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgSelectModule } from '@ng-select/ng-select';
import {  ElementRef, ViewChild } from '@angular/core';
import { Modal } from 'bootstrap';

// import { Modal } from 'bootstrap';
// import modal


@Component({
  selector: 'app-doctordashavailabletimings',
  standalone: true,
  imports: [NgSelectModule, FormsModule, CommonModule],
  templateUrl: './doctordashavailabletimings.component.html',
  styleUrl: './doctordashavailabletimings.component.scss'
})
export class DoctordashavailabletimingsComponent implements AfterViewInit, OnInit{
  clinics: Clinic[] = [];
  selectedClinicId!: number;
  selectedDay: DayOfWeek = 'SATURDAY';
  daySlots: DaySlot[] = [];
  dayList = [
  { key: 'MONDAY', label: 'Monday' },
  { key: 'TUESDAY', label: 'Tuesday' },
  { key: 'WEDNESDAY', label: 'Wednesday' },
  { key: 'THURSDAY', label: 'Thursday' },
  { key: 'FRIDAY', label: 'Friday' },
  { key: 'SATURDAY', label: 'Saturday' },
  { key: 'SUNDAY', label: 'Sunday' },
] as { key: DayOfWeek, label: string }[];

startHour = 9;
startMinute = 0;
endHour = 12;
endMinute = 0;
durationMinutes = 30; // e.g., user selection
intervalMinutes = 15; // e.g., user selection


    @ViewChild('addSlotModalRef') addSlotModalRef!: ElementRef;
  private addSlotModal!: Modal;


  private modalInstance!: Modal;
 
    // Form values
 newSlot: SlotFormModel = {
  startTimeStr: '',
  endTimeStr: '',
  durationMinutes: 30,
  intervalMinutes: 10,
};

   private initTime(): TimeObject {
    return { hour: 0, minute: 0, second: 0, nano: 0 };
  }

  


    constructor(private doctorService: DoctorService) {}
    

  ngOnInit(): void {
    this.doctorService.getMyClinics().subscribe(data => {
      this.clinics = data;
      console.log(data, "getting clinics")
      this.selectedClinicId = data[0]?.id;
      this.fetchSlots(this.selectedClinicId, this.selectedDay);
    });
    this.addSlotModal = new Modal(this.addSlotModalRef.nativeElement);
    

  }
  formatTime(slot: { hour: number, minute: number }): string {
    if (!slot) return '';
    const hour = slot.hour % 12 || 12;
    const minute = slot.minute?.toString().padStart(2, '0');
    const suffix = slot.hour >= 12 ? 'PM' : 'AM';
    return `${hour}:${minute} ${suffix}`;
}
  fetchSlots(clinicId: number, day: DayOfWeek) {
    console.log("fetching slots on onit")
    this.doctorService.getDaySlots(clinicId, day).subscribe({
      next: (slotsMap) => {
        this.daySlots = slotsMap[day] || [];
      },
      error: (err) => {
        console.error(`Failed to fetch slots for ${day}:`, err);
        this.daySlots = []; // fallback to empty slots
      }
    });
  }
  onDayChange(day: DayOfWeek, event: Event) {
  event.preventDefault(); // prevent page jump
  this.selectedDay = day;
  this.fetchSlots(this.selectedClinicId, day); // load data
  }

    

   ngAfterViewInit() {
    if (this.addSlotModalRef?.nativeElement) {
      this.addSlotModal = new Modal(this.addSlotModalRef.nativeElement, {
        backdrop: true
      });
    }
  }
openAddSlotModal(event?: Event) {
    event?.preventDefault();
    this.addSlotModal?.show();
  }

  closeAddSlotModal() {
    this.addSlotModal?.hide();
  }



 saveSlot(): void {
  try {
    const startParts = this.newSlot.startTimeStr.split(":").map(Number);
    const endParts = this.newSlot.endTimeStr.split(":").map(Number);
    const durationISO = `PT${this.newSlot.durationMinutes}M`;
     const intervalISO = `PT${this.newSlot.intervalMinutes}M`;

    if (startParts.length !== 2 || endParts.length !== 2) {
      alert('Please enter valid time in HH:mm format.');
      return;
    }

    const startTimeString = `${startParts[0].toString().padStart(2, '0')}:${startParts[1].toString().padStart(2, '0')}`;
    const endTimeString = `${endParts[0].toString().padStart(2, '0')}:${endParts[1].toString().padStart(2, '0')}`;

    const slotDto: SlotDTO = {
      startTime: startTimeString,
      endTime: endTimeString,
      duration: durationISO,
      interval: intervalISO
    };

    this.doctorService
      .postSlots(this.selectedDay, this.selectedClinicId, slotDto)
      .subscribe({
        next: () => {
          this.closeAddSlotModal();
          this.fetchSlots(this.selectedClinicId, this.selectedDay);
        },
        error: (err) => {
          console.error('Error saving slot:', err);
          alert('Failed to save slot.');
        },
      });

  } catch (e) {
    console.error('Invalid input:', e);
    alert('Invalid time format');
  }
}

  buildTime(hour: number, minute: number): string {
  return `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:00`;
}

}
