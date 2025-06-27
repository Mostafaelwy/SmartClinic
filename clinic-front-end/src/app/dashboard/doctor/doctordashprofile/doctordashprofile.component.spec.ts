import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashprofileComponent } from './doctordashprofile.component';

describe('DoctordashprofileComponent', () => {
  let component: DoctordashprofileComponent;
  let fixture: ComponentFixture<DoctordashprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashprofileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
