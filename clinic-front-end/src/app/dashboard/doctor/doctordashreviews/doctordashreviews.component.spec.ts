import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashreviewsComponent } from './doctordashreviews.component';

describe('DoctordashreviewsComponent', () => {
  let component: DoctordashreviewsComponent;
  let fixture: ComponentFixture<DoctordashreviewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashreviewsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashreviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
