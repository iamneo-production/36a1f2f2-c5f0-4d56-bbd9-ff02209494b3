import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletegroundComponent } from './deleteground.component';

describe('DeletegroundComponent', () => {
  let component: DeletegroundComponent;
  let fixture: ComponentFixture<DeletegroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletegroundComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletegroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
