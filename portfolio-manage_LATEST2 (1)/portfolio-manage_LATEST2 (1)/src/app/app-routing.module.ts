import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePortfolioComponent } from './create-portfolio/create-portfolio.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RebalancePageComponent } from './rebalance-page/rebalance-page.component';
import { OptimizePortfolioComponent } from './optimize-portfolio/optimize-portfolio.component';
import { PortfolioPredictionComponent } from './portfolio-prediction/portfolio-prediction.component';
import { RecommendedPortfolioComponent } from './recommended-portfolio/recommended-portfolio.component';

const routes: Routes = [
  {path:'prediction', component:PortfolioPredictionComponent},
  {path:'create', component:CreatePortfolioComponent},
  {path:'home', component:HomePageComponent},
  {path:'rebalance', component:RebalancePageComponent},
  {path:'recommend', component:RecommendedPortfolioComponent},
  {path:'optimize', component:OptimizePortfolioComponent}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
