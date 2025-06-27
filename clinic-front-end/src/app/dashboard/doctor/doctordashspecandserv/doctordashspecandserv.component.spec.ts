import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctordashspecandservComponent } from './doctordashspecandserv.component';

describe('DoctordashspecandservComponent', () => {
  let component: DoctordashspecandservComponent;
  let fixture: ComponentFixture<DoctordashspecandservComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctordashspecandservComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctordashspecandservComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
