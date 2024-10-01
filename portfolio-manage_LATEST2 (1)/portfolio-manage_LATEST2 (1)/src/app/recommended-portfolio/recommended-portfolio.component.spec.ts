import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendedPortfolioComponent } from './recommended-portfolio.component';

describe('RecommendedPortfolioComponent', () => {
  let component: RecommendedPortfolioComponent;
  let fixture: ComponentFixture<RecommendedPortfolioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecommendedPortfolioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendedPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
