import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditgroundComponent } from './editground.component';

describe('EditgroundComponent', () => {
  let component: EditgroundComponent;
  let fixture: ComponentFixture<EditgroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditgroundComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditgroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
