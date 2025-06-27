import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentListMiniComponent } from './appointment-list-mini.component';

describe('AppointmentListMiniComponent', () => {
  let component: AppointmentListMiniComponent;
  let fixture: ComponentFixture<AppointmentListMiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentListMiniComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentListMiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
