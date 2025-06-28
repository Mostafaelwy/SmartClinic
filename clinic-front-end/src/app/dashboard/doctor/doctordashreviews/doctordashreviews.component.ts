import { Component } from '@angular/core';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { PagedReviews, Review } from '../../../../types';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-doctordashreviews',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './doctordashreviews.component.html',
  styleUrl: './doctordashreviews.component.scss'
})
export class DoctordashreviewsComponent {
  reviews: Review[] = [];
  page: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  totalElements: number = 0;
  sortAttribute: string = 'creationDate';
  dir: 'ASC' | 'DESC' = 'DESC';
  startDate: string = '';
  endDate: string = '';

  constructor(private doctorService: DoctorService) {
    const today = new Date();
    const lastMonth = new Date();
    lastMonth.setMonth(today.getMonth() - 1);
    this.startDate = lastMonth.toISOString().slice(0, 10);
    this.endDate = today.toISOString().slice(0, 10);
    this.fetchReviews();
  }

  get averageRating(): string {
    if (!this.reviews.length) return '0.0';
    const avg = this.reviews.map(r => r.rate).reduce((a, b) => a + b, 0) / this.reviews.length;
    return avg.toFixed(1);
  }

  get averageRatingRounded(): number {
    if (!this.reviews.length) return 0;
    return Math.round(this.reviews.map(r => r.rate).reduce((a, b) => a + b, 0) / this.reviews.length);
  }

  get isDateRangeValid(): boolean {
    return this.startDate <= this.endDate;
  }

  fetchReviews() {
    const params: {
      pageNum: number;
      pageSize: number;
      sortAttripute: string;
      dir: 'ASC' | 'DESC';
      startDate: string;
      endDate: string;
    } = {
      pageNum: this.page,
      pageSize: this.pageSize,
      sortAttripute: this.sortAttribute,
      dir: this.dir,
      startDate: this.startDate,
      endDate: this.endDate
    };
    this.doctorService.getReviews(params).subscribe((res: PagedReviews) => {
      this.reviews = res.content;
      this.totalPages = res.totalPages;
      this.totalElements = res.totalElements;
    });
  }

  goToPage(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.page = page;
      this.fetchReviews();
    }
  }

  nextPage() {
    this.goToPage(this.page + 1);
  }

  prevPage() {
    this.goToPage(this.page - 1);
  }

  onDateChange(start: string, end: string) {
    this.startDate = start;
    this.endDate = end;
    this.page = 0;
    if (this.isDateRangeValid) {
      this.fetchReviews();
    }
  }
}
