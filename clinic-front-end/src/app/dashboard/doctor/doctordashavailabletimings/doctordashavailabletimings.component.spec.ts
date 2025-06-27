import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashavailabletimingsComponent } from './doctordashavailabletimings.component';

describe('DoctordashavailabletimingsComponent', () => {
  let component: DoctordashavailabletimingsComponent;
  let fixture: ComponentFixture<DoctordashavailabletimingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashavailabletimingsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashavailabletimingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
