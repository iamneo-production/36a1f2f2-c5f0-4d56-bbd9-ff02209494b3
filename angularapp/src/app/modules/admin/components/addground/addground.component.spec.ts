import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddgroundComponent } from './addground.component';

describe('AddgroundComponent', () => {
  let component: AddgroundComponent;
  let fixture: ComponentFixture<AddgroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddgroundComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddgroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
