import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionFormComponent } from './prediction-form.component';

describe('PredictionFormComponent', () => {
  let component: PredictionFormComponent;
  let fixture: ComponentFixture<PredictionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredictionFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
