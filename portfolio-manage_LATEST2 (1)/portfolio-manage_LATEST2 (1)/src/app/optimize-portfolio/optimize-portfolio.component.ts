import { Component, OnInit } from '@angular/core';
import 'chartjs-plugin-annotation';
import { Color, Label } from 'ng2-charts';
import { Chart, ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { OptimizeService } from '../Service/optimizePortfolio/optimize.service';

export class ChartData {
  constructor(
    public portfolio_id:string,
    public date:Date,
    public monte_carlo:number,
    public dl:number,
    public dynamic_dl:number
  ) {}
}

export class LineHelper {
  constructor(
    public monteCarlo:number[],
    public dl:number[],
    public dynamicDl:number[],
    public dt:Date[],
    public nifty50:number[]
  ){}
}

@Component({
  selector: 'app-optimize-portfolio',
  templateUrl: './optimize-portfolio.component.html',
  styleUrls: ['./optimize-portfolio.component.css']
})
export class OptimizePortfolioComponent implements OnInit {
  chart: any;

  constructor(private optimizeService:OptimizeService){
  
  }
  
  ngOnInit(): void {
    this.getPortfolioNameList()
    this.portfolioName="Portfolio 1"
    this.getLineChartInfo() 
  }
  public lineHelper:LineHelper;
  public portfolioNameList:string[]=[]
  public portfolioName:string

  public monte_carlo:number[]=[]
  public dl:number[]=[]
  public dynamic_dl:number[]=[]
  public date:Date[]=[]
  public nifty50:number[]

  // public chart=[];

  lineChartData: ChartDataSets[] = [];

  lineChartLabels: Array<any>=[];

  lineChartOptions = {
    responsive: true,
  };
  

  lineChartColors: Color[] = [
    {
      // backgroundColor: ['#FF8E00', '#781D42', '#fdf57d','#009DAE','71DFE7','D06224','66806A','#753188','#2FDD92'],
      borderColor: ['#FF8E00', '#781D42', 'rgba(241, 107, 119, 0.2)']

    },
  ];

  lineChartLegend = true;
  lineChartPlugins = [];
  public lineChartType: ChartType = "line";

  getPortfolioNameList() {
    this.optimizeService.getPortfolioNameList(1).subscribe(
      response => {
        this.portfolioNameList=response
        console.log(this.portfolioNameList)
        this.portfolioNameList.push("REC_1")
        this.portfolioNameList.push("REC_2")
      }
    )
  }


  getLineChartInfo() {
    this.lineChartData=[]
    console.log(this.portfolioName)

    this.optimizeService.getLineChartData(this.portfolioName).subscribe(
      response=>{
        this.lineHelper= response ;
        this.monte_carlo=this.lineHelper.monteCarlo ;
        console.log(`monte carlo - ${this.monte_carlo}`) ;
        this.dynamic_dl=this.lineHelper.dynamicDl ;
        this.nifty50=this.lineHelper.nifty50 ;
        this.dl=this.lineHelper.dl ;
        console.log('============================================================================')
        console.log("NIFTY50")
        console.log( this.nifty50 )
        console.log('============================================================================')

        this.date=this.lineHelper.dt;
        this.lineChartLabels=this.date;
        var aaa:ChartDataSets[] = [{data:this.monte_carlo, label: 'Monte Carlo',fill: false, type: 'line'},
                                   {data:this.dl, label: 'DL',fill: false},
                                  {data:this.nifty50,label:'Nifty50',fill: false,type: 'line'},
                                  ]
                                  // {data:this.dynamic_dl,label:'Dynamic_DL',fill: false},
        this.lineChartData=aaa


      
      
      }
    )

  }


  


}
