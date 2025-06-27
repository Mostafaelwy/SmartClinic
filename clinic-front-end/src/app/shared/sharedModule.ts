import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxDaterangepickerMd } from 'ngx-daterangepicker-material';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxDaterangepickerMd.forRoot() // ✅ must be used here
  ],
  exports: [
    CommonModule,
    FormsModule,
    NgxDaterangepickerMd
  ]
})
export class SharedModule {}
