import { Component, OnInit } from '@angular/core';
import { PortFoiloPredictionService } from '../Service/portfolioprediction/port-foilo-prediction.service';

export class PortfolioPrediction {
  constructor(
    public performance_id:number,
    public portfolio_id:string,
    public percentage_return:number,
    public latest_value:number,
    public overall_gain:number,
    public investment_Cost:number,
    public todays_gain:number,
    public portfolio_beta:number,
    public sharpe_ratio:number,
    public jensens_alpha:number,
    public value_at_risk:number,
    public month:string,
    
  ) {}
  }

@Component({
  selector: 'app-portfolio-prediction',
  templateUrl: './portfolio-prediction.component.html',
  styleUrls: ['./portfolio-prediction.component.css']
})
export class PortfolioPredictionComponent implements OnInit {
  public portfolioName: string[];
  public portfolioPrediction: PortfolioPrediction[];
  portName: string;
  
  constructor(private service:PortFoiloPredictionService) { }

  ngOnInit(): void {
    this.getPortfolioNames()
    this.portName="Portfolio 1"
    this.getinfo() 
  }

  getPortfolioNames() {
    console.log("getAllportfolioName")
    this.service.getPortfolioName(1).subscribe( 
      response => {

        console.log(response)
         this.portfolioName= response;

        console.log(this.portName)
      }
    )  
  }

//Fetch all predicted values
  getinfo() {         
    console.log("getinfo")
    this.service.getPredictionValue(this.portName).subscribe( 
      response => {
        console.log(response)
         this.portfolioPrediction= response;
        console.log(this.portfolioPrediction)
      }
    )  
  }


}
