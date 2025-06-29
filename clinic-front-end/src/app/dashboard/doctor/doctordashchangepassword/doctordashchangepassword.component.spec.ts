import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashchangepasswordComponent } from './doctordashchangepassword.component';

describe('DoctordashchangepasswordComponent', () => {
  let component: DoctordashchangepasswordComponent;
  let fixture: ComponentFixture<DoctordashchangepasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashchangepasswordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashchangepasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
