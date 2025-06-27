import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashrequestsComponent } from './doctordashrequests.component';

describe('DoctordashrequestsComponent', () => {
  let component: DoctordashrequestsComponent;
  let fixture: ComponentFixture<DoctordashrequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashrequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashrequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
