import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ChartsModule } from 'ng2-charts';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortfolioPredictionComponent } from './portfolio-prediction/portfolio-prediction.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CreatePortfolioComponent } from './create-portfolio/create-portfolio.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RebalancePageComponent } from './rebalance-page/rebalance-page.component';
import { RecommendedPortfolioComponent } from './recommended-portfolio/recommended-portfolio.component';
import { OptimizePortfolioComponent } from './optimize-portfolio/optimize-portfolio.component';
import { shoternDecimalNumPipe } from './shared/Pipes/shortenDecimalNum.pipe';


@NgModule({
  declarations: [
    AppComponent,
    PortfolioPredictionComponent,
    NavbarComponent,
    CreatePortfolioComponent,
    HomePageComponent,
    RebalancePageComponent,
    RecommendedPortfolioComponent,
    OptimizePortfolioComponent,
    shoternDecimalNumPipe
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule,
    ChartsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
