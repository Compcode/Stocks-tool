import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OptimizePortfolioComponent } from './optimize-portfolio.component';

describe('OptimizePortfolioComponent', () => {
  let component: OptimizePortfolioComponent;
  let fixture: ComponentFixture<OptimizePortfolioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OptimizePortfolioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OptimizePortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
