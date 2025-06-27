import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashappointmentComponent } from './doctordashappointment.component';

describe('DoctordashappointmentComponent', () => {
  let component: DoctordashappointmentComponent;
  let fixture: ComponentFixture<DoctordashappointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashappointmentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
