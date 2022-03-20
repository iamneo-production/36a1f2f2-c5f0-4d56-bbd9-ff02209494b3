import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditgrdComponent } from './editgrd.component';

describe('EditgrdComponent', () => {
  let component: EditgrdComponent;
  let fixture: ComponentFixture<EditgrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditgrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditgrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
