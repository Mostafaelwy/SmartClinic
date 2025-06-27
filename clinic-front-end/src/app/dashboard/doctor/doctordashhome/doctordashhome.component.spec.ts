import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashhomeComponent } from './doctordashhome.component';

describe('DoctordashhomeComponent', () => {
  let component: DoctordashhomeComponent;
  let fixture: ComponentFixture<DoctordashhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashhomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
