import { Component, OnInit } from '@angular/core';
import { ChartType } from 'chart.js';
import { MultiDataSet, Label } from 'ng2-charts';
import { AssetWeights, Portfolio_Performance } from '../home-page/home-page.component';
import { RecommendService } from '../Service/recommendPortfolio/recommend.service';

/*export class RecommendedAssetWeights {
  constructor(
    public asset_id:string[],
    public portfolio_id:string[],
    public weight:number[],
    public stocklist:string[]
  ) {}
} */

export class SectorWiseStockCount {

  constructor(
    public stockName:string,
    public sector_wise_count:number
  ) {}
}

export class SectorData {

  constructor(
    public company_name:string,
    public industry:string,
    public asset_id:string,
    public series:string,
    public isin_code:string,

    public count:number
    
  ) {}
} 

@Component({
  selector: 'app-recommended-portfolio',
  templateUrl: './recommended-portfolio.component.html',
  styleUrls: ['./recommended-portfolio.component.css']
})
export class RecommendedPortfolioComponent implements OnInit {
  public portfolioNameList:string[]
  public counter:number
  public sector:string[]
  public stocksList:string[]

  public industry:string[]
  public count:number[]
  public sectorWiseStockCount:SectorWiseStockCount[]

  public portfolio_id1:string;
  public five_year_return:number;
  public sharpe_ratio:number;
  public value_at_risk:number;
  public portfolio_beta:number;
  public treynor_ratio:number;
  public jensens_alpha:number;

  public portfolioName:string
  public assetWeights:AssetWeights[]
  public asset_id:string[]=[]
  public portfolio_id:number[]=[]

  public weight:number[]=[]
  //public recommendedassetWeights:RecommendedAssetWeights;

  public doughnutChartLabels: Label[] = [];
  public doughnutChartData: number[] = [];
  public doughnutChartType: ChartType = 'doughnut';

  public doughnutChartLabels2: Label[] = [];
  public doughnutChartData2: number[] = [];
  public doughnutChartType2: ChartType = 'doughnut';

  public portfolio_Performance:Portfolio_Performance;




  public pieChartColors: Array < any > = [{
    backgroundColor: ['#0F2C67', '#CD1818', '#F3950D','#F4E185','#396EB0','#396EB0','#DADDFC','#FC997C','#66806A',
  '#E6CCA9','#533535','#AE4CCF','#864879','#105652','#FEF9EF','#316B83','#C1FFD7','#a68a64','#a4ac86','#b5838d'],
    borderColor: ['rgba(252, 235, 89, 0.2)', 'rgba(77, 152, 202, 0.2)', 'rgba(241, 107, 119, 0.2)']
  }];

  constructor(private recommendService:RecommendService) { }

  ngOnInit(): void {
    this.getPortfolioNameList();
    this.portfolioName="REC_1"
    this.getPieChartInfo() 
    
  }

  getPortfolioNameList() {
    this.recommendService.getPortfolioNameList(1).subscribe(
      response => {
        this.portfolioNameList=response
        console.log(this.portfolioNameList)
      }
    )
  }

  getRecommendedStockList() {
    this.recommendService.getStockList(this.portfolioName).subscribe(
      response => {
        this.sector = response
        console.log(this.sector)
      }
    )
  }

  getRecSectorStockList() {
    this.recommendService.getSectorStockList(this.portfolioName).subscribe(
      response => {
        this.stocksList = response
        console.log(this.stocksList)
      }
    )
  }

  getPieChartInfo() {
    this.doughnutChartLabels=[]
    this.doughnutChartData=[]
    this.asset_id=[]
    this.portfolio_id=[]

    this.five_year_return =0
    this.sharpe_ratio = 0
    this.value_at_risk = 0
    this.portfolio_beta = 0
    this.treynor_ratio = 0
    this.jensens_alpha = 0
  
    this.recommendStockPieChartInfo();
    this.portfolioPerformance();  
    this.getRecommendedStockList();
    this.getRecSectorStockList();  
    
    this.getRecSectorPieChart();
    this.recommendSectorPieChartInfo();
  }

  getRecSectorPieChart() {
    this.doughnutChartLabels2=[]
    this.doughnutChartData2=[]
    this.industry=[]
    this.count=[]
  }

  recommendSectorPieChartInfo() {
    this.recommendService.getRecSectorChartData(this.portfolioName).subscribe(
      response => {
        this.sectorWiseStockCount=response
        console.log(`sector wise stock count response = ${response}`)

        console.log(`sector wise count = ${this.sectorWiseStockCount}`)

        console.log(`sectorWiseStockCount length - ${this.sectorWiseStockCount.length}`)
        for (let i = 0; i < this.sectorWiseStockCount.length; i++) {
          console.log("inside for loop")
          console.log(this.sectorWiseStockCount[i].stockName)
          console.log(this.sectorWiseStockCount[i].sector_wise_count)
    
          var a:string=this.sectorWiseStockCount[i].stockName
          var b:number =this.sectorWiseStockCount[i].sector_wise_count
        console.log(a)
        console.log(b)
         var l= this.industry.push(a)
          l=this.count.push(b)
        }
        console.log(this.industry)
        console.log(this.count)
        this.doughnutChartLabels2 = this.industry
        this.doughnutChartData2 = this.count 
      }

    )
  }

  recommendStockPieChartInfo() {
    this.recommendService.getAssetWeightsData(this.portfolioName).subscribe(
      response => {
        this.assetWeights=response
        console.log(this.assetWeights)

        console.log(`assetWeight length - ${this.assetWeights.length}`)
        for (let i = 0; i < this.assetWeights.length; i++) {
          console.log("inside for loop")
          console.log(this.assetWeights[i].asset_id)
          console.log(this.assetWeights[i].weight)

          console.log(this.assetWeights[i].counter)
          this.counter = this.assetWeights.length;
    
          var a:string=this.assetWeights[i].asset_id
          var b:number =this.assetWeights[i].weight
        console.log(a)
        console.log(b)
         var l= this.asset_id.push(a)
          l=this.portfolio_id.push(b)
        }
        console.log(this.asset_id)
        console.log(this.portfolio_id)
        this.doughnutChartLabels=this.asset_id
        this.doughnutChartData= this.portfolio_id
    
      }

    )

  }


  portfolioPerformance() {

    this.recommendService.getPortfolioPerformance(this.portfolioName).subscribe(
      response => {
        this.portfolio_Performance=response
        console.log(response)
        this.portfolio_id1 = this.portfolio_Performance.portfolio_id;
         this.five_year_return =this.portfolio_Performance.five_year_return
         this.sharpe_ratio = this.portfolio_Performance.sharpe_ratio
         this.value_at_risk = this.portfolio_Performance.value_at_risk
         this.portfolio_beta = this.portfolio_Performance.portfolio_beta
         this.treynor_ratio = this.portfolio_Performance.treynor_ratio
         this.jensens_alpha = this.portfolio_Performance.jensens_alpha
      
      }
    )
  }


}
