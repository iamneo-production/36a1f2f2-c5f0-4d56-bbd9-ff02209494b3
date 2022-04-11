import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrounddetailsComponent } from './grounddetails.component';

describe('GrounddetailsComponent', () => {
  let component: GrounddetailsComponent;
  let fixture: ComponentFixture<GrounddetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GrounddetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GrounddetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
