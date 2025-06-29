import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DoctorService } from '../../../services/doctor/doctor.service';

@Component({
  selector: 'app-doctordashchangepassword',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './doctordashchangepassword.component.html',
  styleUrl: './doctordashchangepassword.component.scss'
})
export class DoctordashchangepasswordComponent {
  showNewPassword = false;
  showConfirmPassword = false;

  oldPassword = '';
  newPassword = '';
  confirmPassword = '';
  message = '';
  error = '';
  loading = false;

  constructor(private doctorService: DoctorService) {}

  toggleNewPassword() {
    this.showNewPassword = !this.showNewPassword;
  }

  toggleConfirmPassword() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  onSubmit() {
    this.message = '';
    this.error = '';
    if (!this.oldPassword || !this.newPassword || !this.confirmPassword) {
      this.error = 'All fields are required.';
      return;
    }
    if (this.newPassword !== this.confirmPassword) {
      this.error = 'New password and confirm password do not match.';
      return;
    }
    this.loading = true;
    this.doctorService.changePassword(this.oldPassword, this.newPassword).subscribe({
      next: () => {
        this.message = 'Password changed successfully!';
        this.oldPassword = this.newPassword = this.confirmPassword = '';
        this.loading = false;
      },
      error: (err: any) => {
        this.error = err?.error?.message || 'Failed to change password.';
        this.loading = false;
      }
    });
  }
}
