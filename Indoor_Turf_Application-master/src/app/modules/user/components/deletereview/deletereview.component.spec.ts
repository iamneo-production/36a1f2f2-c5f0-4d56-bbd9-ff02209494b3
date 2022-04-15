import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletereviewComponent } from './deletereview.component';

describe('DeletereviewComponent', () => {
  let component: DeletereviewComponent;
  let fixture: ComponentFixture<DeletereviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletereviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletereviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
