import { NumberSymbol } from '@angular/common';
import { StringMap } from '@angular/compiler/src/compiler_facade_interface';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { splitAtColon } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { ChartType } from 'chart.js';
import { MultiDataSet, Label } from 'ng2-charts';
import { pairs } from 'rxjs';
import { PortfolioInfo } from '../create-portfolio/create-portfolio.component';
import { HomeService } from '../Service/homePage/home.service';



export class AssetWeights {
  constructor(
    public asset_id:string,
    public asset_type:string,
    public portfolio_id:string,
    public weight:number,
    public amount:number,
//get stocks count
    public counter:number,
    public percent:string

  ) {}
} 


 export class SectorData {

  constructor(
    public company_name:string,
    public industry:string,
    public asset_id:string,
    public series:string,
    public isin_code:string,

    //public count:number
    
  ) {}
}  

export class SectorWiseCount {

  constructor(
    public stockName:string,
    public stockCount:number
    
  ) {}
}  

export class Portfolio_Performance {
  constructor(
    public portfolio_id:string,
    public five_year_return:number,
    public sharpe_ratio:number,
    public value_at_risk:number,
    public portfolio_beta:number,
    public treynor_ratio:number,
    public jensens_alpha:number

  ) {}
}


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  public portfolioNameList:string[]
  public portfolioName:string
  public assetWeights:AssetWeights[]
  public asset_id:string[]=[]
  public portfolio_id:number[]=[]
  public sectorWiseCount:SectorWiseCount[]

//List data
  public sectorData:SectorData[]
  public counter:number
  public sector:string[]

  public industry:string[]
  public count:number[]

  public stockList:string[]


  public doughnutChartLabels: Label[] = [];
  public doughnutChartData: number[] = [];
  public doughnutChartType: ChartType = 'doughnut';

  public doughnutChartLabels2: Label[] = [];
  public doughnutChartData2: number[] = [];
  public doughnutChartType2: ChartType = 'doughnut';


  public portfolio_Performance:Portfolio_Performance;

  public portfolio_id1:string;
  public five_year_return:number;
  public sharpe_ratio:number;
  public value_at_risk:number;
  public portfolio_beta:number;
  public treynor_ratio:number;
  public jensens_alpha:number;

  public pieChartColors: Array < any > = [{
    backgroundColor: ['#0F2C67', '#CD1818', '#F3950D','#F4E185','#396EB0','#396EB0','#DADDFC','#FC997C','#66806A',
  '#E6CCA9','#533535','#AE4CCF','#864879','#105652','#FEF9EF','#316B83','#C1FFD7','#a68a64','#a4ac86','#b5838d'],
    borderColor: ['rgba(252, 235, 89, 0.2)', 'rgba(77, 152, 202, 0.2)', 'rgba(241, 107, 119, 0.2)']
  }];
 
 
  // Trying GoogleChart for PiChart 
  title = 'Browser market shares at a specific website, 2014';
  type = 'PieChart';
  data = [
     ['Firefox', 45.0],
     ['IE', 26.8],
     ['Chrome', 12.8],
     ['Safari', 8.5],
     ['Opera', 6.2],
     ['Others', 0.7] 
  ];
  columnNames = ['Browser', 'Percentage'];
  options = {    
  };
  width = 550;
  height = 400;
   // Trying GoogleChart for PiChart End

  constructor(private homeService:HomeService) { }

  ngOnInit() {
    this.getPortfolioNameList()
    this.portfolioName="Portfolio 1"
    this.getPieChartInfo()
  }



  getPortfolioNameList() {
    this.homeService.getPortfolioNameList(1).subscribe(
      response => {
        this.portfolioNameList=response
        console.log(this.portfolioNameList)
      }
    )
  }

//To display sector names chosen
  getSectorNameList() {
    this.homeService.getSectorNameList(this.portfolioName).subscribe(
      response => {
        this.sector = response
        console.log("---------------------------")   
        console.log("sector variable is -", this.sector)

        this.stockPieChartInfo() ;
        this.sectorPieChartInfo() ;
       
      }
      
    )
  } 

//Fetch stocks and its sector name
  getStockList() {
    this.homeService.getStockList(this.portfolioName).subscribe(
      response => {
        this.stockList = response
        console.log(this.stockList)

        
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
  
    this.portfolioPerformance() ;
    this.getSectorNameList() ;
    this.getStockList() ;
    this.getSectorChartInfo() ;

    // this.stockPieChartInfo() ;
    // this.sectorPieChartInfo() ;

  }

  getSectorChartInfo() {
    this.doughnutChartLabels2=[]
    this.doughnutChartData2=[]
    this.industry=[]
    this.count=[]

   
  }

//Sector data displayed on pie chart
  sectorPieChartInfo() {
    this.homeService.getSectorData(this.portfolioName).subscribe(
      response => {
        this.sectorWiseCount=response
        console.log(`sector wise count response = ${response}`)

        console.log("sector wise count = " ,this.sectorWiseCount)

        console.log(`sectorWiseCount length - ${this.sectorWiseCount.length}`)
        for (let i = 0; i < this.sectorWiseCount.length; i++) {
          console.log("inside for loop")
          console.log(this.sectorWiseCount[i].stockName )
          console.log(this.sectorWiseCount[i].stockCount)
    
          var a:string=this.sectorWiseCount[i].stockName
          var b:number =this.sectorWiseCount[i].stockCount
        console.log(a)
        console.log(b)

       // if()

        if(a!=null) var l= this.industry.push(a +" Stocks")
       if(b!=0)   l=this.count.push(b)
          //console.log("l value is - ",l)
        }
        console.log(this.industry)
        console.log(this.count)
        this.doughnutChartLabels2 = this.industry ;
        this.doughnutChartData2 = this.count ;
      }

    )
  }

//Display portfolio stocks data on pie chart
  stockPieChartInfo() {
    this.homeService.getAssetWeightsData(this.portfolioName).subscribe(
      response => {
        this.assetWeights=response
        console.log("assetWeights -- ",response)

        console.log(`assetWeight length - ${this.assetWeights.length}`)

        // count frequency of mutual fund hourses like SBI has total 24 items

         // default is undefined
        let sectorMap = new Map() ;
      

        for (let i = 0; i < this.assetWeights.length; i++) {
          console.log("inside for loop")
          console.log(this.assetWeights[i].asset_id)
          console.log(this.assetWeights[i].weight)

          console.log(this.assetWeights[i].counter)
          this.counter = this.assetWeights.length
    
          var a:string=this.assetWeights[i].asset_id
          var b:number =this.assetWeights[i].weight
          var asset_type:string = this.assetWeights[i].asset_type;
          
        console.log(a)
        console.log(b)
        
         let firstname:string  = a.split(" ")[0];

          if(asset_type=="stock")
          {
             this.asset_id.push( firstname + " Stock") ;
             this.portfolio_id.push(b) ;
          }
          else if(asset_type=="mutualFund")
          {
                 if(sectorMap.get(firstname)==undefined)
                 {
                   sectorMap.set(firstname,1)
                 }
                 else{
                  sectorMap.set(firstname, sectorMap.get(firstname) )
                 }

                 this.asset_id.push( firstname + " Mutual Funds") ;
                 this.portfolio_id.push(b) ;
               
          }
          else if(asset_type=="bond")
          {
                 if(sectorMap.get(firstname)==undefined)
                 {
                   sectorMap.set(firstname,1)
                 }
                 else{
                  sectorMap.set(firstname, sectorMap.get(firstname) )
                 }

                 this.asset_id.push( firstname + " bonds ") ;
                 this.portfolio_id.push(b) ;
               
          }


        }
        console.log(this.asset_id)
        console.log(this.portfolio_id)
        this.doughnutChartLabels= this.asset_id
        this.doughnutChartData= this.portfolio_id

        // ------------------------

        for(let  entry of sectorMap.entries()){
          this.industry.push(entry[0]+" Mutual Funds") ;
          this.count.push(entry[1]) ;
          
          console.log("to push in sector", entry[0]+" Mutual Funds,"+ entry[1].toString())
          this.sector.push(entry[0]+" Mutual Funds,"+ entry[1].toString())
        }

        // this.industry.push("trial sbi") ;
        // this.count.push(12)
        this.doughnutChartLabels2 = this.industry ;
        this.doughnutChartData2 = this.count ;
         // ------------------------

        // this.sector.push("sasa,2")

      }

    )

  }


  portfolioPerformance() {

    this.homeService.getPortfolioPerformance(this.portfolioName).subscribe(
      response => {
        this.portfolio_Performance=response
        console.log("Portfolio Perfomance ratio")
        
        this.portfolio_id1 = this.portfolio_Performance.portfolio_id;
        this.five_year_return =this.portfolio_Performance.five_year_return
        this.sharpe_ratio = this.portfolio_Performance.sharpe_ratio
        this.value_at_risk = this.portfolio_Performance.value_at_risk
        this.portfolio_beta = this.portfolio_Performance.portfolio_beta
        this.treynor_ratio = this.portfolio_Performance.treynor_ratio
        this.jensens_alpha = this.portfolio_Performance.jensens_alpha
        
        console.log(response)
        console.log( this.portfolio_id1)
        console.log( this.five_year_return)
        console.log( this.sharpe_ratio)
        console.log( this.value_at_risk)
        console.log( this.portfolio_beta)
        console.log( this.treynor_ratio)
        console.log( this.jensens_alpha)
        
      
      }
    )
  }

}
