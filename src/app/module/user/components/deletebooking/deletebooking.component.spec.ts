import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletebookingComponent } from './deletebooking.component';

describe('DeletebookingComponent', () => {
  let component: DeletebookingComponent;
  let fixture: ComponentFixture<DeletebookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletebookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletebookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
