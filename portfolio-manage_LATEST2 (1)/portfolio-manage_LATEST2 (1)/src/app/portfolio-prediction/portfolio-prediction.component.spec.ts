import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioPredictionComponent } from './portfolio-prediction.component';

describe('PortfolioPredictionComponent', () => {
  let component: PortfolioPredictionComponent;
  let fixture: ComponentFixture<PortfolioPredictionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortfolioPredictionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortfolioPredictionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
