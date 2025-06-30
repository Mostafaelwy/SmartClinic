import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppointmentService, Appointment, AppointmentResponse } from '../../../services/appointment.service';

@Component({
    selector: 'app-patientdashappointment',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './patientdashappointment.component.html',
    styleUrl: './patientdashappointment.component.scss'
})
export class PatientdashappointmentComponent implements OnInit {
    loading: boolean = true;
    appointments: Appointment[] = [];
    appointmentResponse: AppointmentResponse | null = null;
    currentPage: number = 0;
    pageSize: number = 10;
    totalPages: number = 0;
    totalElements: number = 0;

    // Tab management
    activeTab: string = 'upcoming';

    // Filter states
    searchTerm: string = '';
    selectedAppointmentType: string = 'all';
    selectedVisitType: string = 'all';
    dateRange: string = '';

    constructor(private appointmentService: AppointmentService) {

    }

    ngOnInit(): void {
        this.initializeDefaultState();
        this.loadAppointments();
    }

    initializeDefaultState(): void {
        // Ensure upcoming tab is set as default
        this.activeTab = 'upcoming';
        this.currentPage = 0;

        // Reset filters to default state
        this.searchTerm = '';
        this.selectedAppointmentType = 'all';
        this.selectedVisitType = 'all';
        this.dateRange = '';
    }

    loadAppointments(): void {
        this.loading = true;
        this.appointmentService.getPatientAppointments(this.currentPage, this.pageSize)
            .subscribe({
                next: (response: AppointmentResponse) => {
                    this.appointmentResponse = response;
                    this.appointments = response.content;
                    this.totalPages = response.totalPages;
                    this.totalElements = response.totalElements;
                    this.loading = false;

                    // Log the default tab state for debugging
                    console.log('Appointments loaded. Active tab:', this.activeTab);
                    console.log('Upcoming appointments count:', this.getUpcomingCount());
                },
                error: (error) => {
                    console.error('Error loading appointments:', error);
                    this.loading = false;

                    // Handle specific error cases
                    if (error.message.includes('Authentication failed')) {
                        // Redirect to login or show login modal
                        console.log('Authentication failed - redirecting to login');
                        // this.router.navigate(['/login']);
                    } else if (error.message.includes('Access denied')) {
                        console.log('Access denied - user may not have permission');
                    }

                    // Set empty arrays to prevent template errors
                    this.appointments = [];
                    this.totalPages = 0;
                    this.totalElements = 0;
                }
            });
    }

    onPageChange(page: number): void {
        this.currentPage = page;
        this.loadAppointments();
    }

    onTabChange(tab: string): void {
        this.activeTab = tab;
        this.currentPage = 0;
        this.loadAppointments();
    }

    getFilteredAppointments(): Appointment[] {
        let filtered = this.appointments;

        // Filter by search term
        if (this.searchTerm) {
            filtered = filtered.filter(appointment =>
                appointment.doctorName.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
                appointment.clinicName.toLowerCase().includes(this.searchTerm.toLowerCase())
            );
        }

        // Filter by appointment type
        if (this.selectedAppointmentType !== 'all') {
            filtered = filtered.filter(appointment =>
                appointment.visitType.toLowerCase() === this.selectedAppointmentType.toLowerCase()
            );
        }

        // Filter by visit type
        if (this.selectedVisitType !== 'all') {
            filtered = filtered.filter(appointment =>
                appointment.visitType.toLowerCase() === this.selectedVisitType.toLowerCase()
            );
        }

        return filtered;
    }

    getAppointmentStatusClass(status: string): string {
        switch (status.toUpperCase()) {
            case 'PENDING':
                return 'badge-soft-warning';
            case 'ACCEPTED':
                return 'badge-soft-purple';
            case 'REJECTED':
                return 'badge-soft-danger';
            case 'COMPLETED':
                return 'badge-soft-success';
            default:
                return 'badge-soft-secondary';
        }
    }

    getAppointmentStatusText(status: string): string {
        switch (status.toUpperCase()) {
            case 'PENDING':
                return 'Pending';
            case 'ACCEPTED':
                return 'Accepted';
            case 'REJECTED':
                return 'Rejected';
            case 'COMPLETED':
                return 'Completed';
            default:
                return status;
        }
    }

    formatAppointmentTime(appointmentTime: any): string {
        if (!appointmentTime) return '';

        const hour = appointmentTime.hour || 0;
        const minute = appointmentTime.minute || 0;
        const ampm = hour >= 12 ? 'PM' : 'AM';
        const displayHour = hour % 12 || 12;
        const displayMinute = minute.toString().padStart(2, '0');

        return `${displayHour}:${displayMinute} ${ampm}`;
    }

    formatAppointmentDate(dateString: string): string {
        if (!dateString) return '';

        const date = new Date(dateString);
        return date.toLocaleDateString('en-US', {
            day: 'numeric',
            month: 'short',
            year: 'numeric'
        });
    }

    getDoctorImageUrl(doctorPhoto: any): string {
        if (doctorPhoto?.url) {
            // Check if it's already a data URL
            if (doctorPhoto.url.startsWith('data:')) {
                return doctorPhoto.url;
            }
            // If it's a valid base64 string, convert to data URL
            if (doctorPhoto.url && this.isValidBase64(doctorPhoto.url)) {
                const mimeType = doctorPhoto.type || 'image/jpeg';
                return `data:${mimeType};base64,${doctorPhoto.url}`;
            }
        }
        return 'assets/img/doctors/doctor-thumb-01.jpg';
    }

    private isValidBase64(str: string): boolean {
        try {
            // Check if string contains only valid base64 characters
            const base64Regex = /^[A-Za-z0-9+/]*={0,2}$/;
            return base64Regex.test(str) && str.length > 0;
        } catch {
            return false;
        }
    }

    getDoctorPhone(doctorNumbers: string[]): string {
        return doctorNumbers && doctorNumbers.length > 0 ? doctorNumbers[0] : 'N/A';
    }

    onSearch(): void {
        this.currentPage = 0;
        this.loadAppointments();
    }

    onFilterChange(): void {
        this.currentPage = 0;
        this.loadAppointments();
    }

    resetFilters(): void {
        this.searchTerm = '';
        this.selectedAppointmentType = 'all';
        this.selectedVisitType = 'all';
        this.dateRange = '';
        this.currentPage = 0;
        this.activeTab = 'upcoming';
        this.loadAppointments();
    }

    getUpcomingAppointments(): Appointment[] {
        return this.getFilteredAppointments().filter(a => a.status === 'PENDING');
    }

    getCancelledAppointments(): Appointment[] {
        return this.getFilteredAppointments().filter(a => a.status === 'REJECTED');
    }

    getCompletedAppointments(): Appointment[] {
        return this.getFilteredAppointments().filter(a => a.status === 'COMPLETED');
    }

    getUpcomingCount(): number {
        return this.getUpcomingAppointments().length;
    }

    getCancelledCount(): number {
        return this.getCancelledAppointments().length;
    }

    getCompletedCount(): number {
        return this.getCompletedAppointments().length;
    }

    // Method to ensure upcoming tab is the default
    setDefaultTab(): void {
        this.activeTab = 'upcoming';
    }

    // Get the current active tab display name
    getActiveTabDisplayName(): string {
        switch (this.activeTab) {
            case 'upcoming':
                return 'Upcoming';
            case 'cancelled':
                return 'Cancelled';
            case 'completed':
                return 'Completed';
            default:
                return 'Upcoming';
        }
    }
} 