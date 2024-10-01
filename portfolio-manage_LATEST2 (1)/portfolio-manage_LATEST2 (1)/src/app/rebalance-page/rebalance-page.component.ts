import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RebalanceService } from '../Service/rebalancePage/rebalance.service';


export class PortfolioInfo {
  constructor(
    public portfolio_id: string,
    public user_id: number,
    public portfolio_name: string,
    public portfolio_investment: number
    
  ) { }
}

export class hh {
  constructor(public p: PortfolioInfo, public s: StockList) { }
}

export class StockList {
  constructor(
    public stockList: string[],
    public stockTypeList:string[] ,
    public percentAlloc: number[],
    public prtID: string
  ) { }
}

@Component({
    selector: 'app-rebalance-page',
    templateUrl: './rebalance-page.component.html',
    styleUrls: ['./rebalance-page.component.css']
  })

 

  export class RebalancePageComponent implements OnInit {

    

    portfolioNameList:string[]
    portfolioName:string
    investmentAmount: number
    stockUn:string[]

    percentAmount: number[]
    selectedStockLTP_NAV: string[]

    mFund: string
    mutualFund: string[]
    mfName: string
    mfList: string[]

bondName: string
    bondList: string[]
    Bond_value: number
    stockClosePrice: string[]
    org_stockPrice: string[]
  
    PortfolioAmt:number
    invAmt:string[]
    stockNames:string[]
    stockNamesType:string[]
    selectedStockType:string[]
    pre_stockNames:string[]
  
    stockPercent:string[] 
    pre_stockPercent:string[] 
    MF_psAllocation:number 
  
    allocationAmt:string[]
    pre_allocationAmt:string[]
    allocationLeft: number    //amount left from stock allocation remaining
  
    org_investmentAmount:number
  
    Cur_investmentAmount:number // for Table
  
    sectorDataRB: string;
    sectorListRB: string[];
    stockListRB: string[];
    stockName: string;
  
    psAllocation: number;
    active: boolean;
  
    selectedStock: string[];
    pecentageAllocation: number[];
  
    totalStockAllocation!: number;
    stockAllocationRemaining: number=0;
    pre_stockAllocationRemaining: number=0;
  
  
  constructor(private rebalanceService:RebalanceService,private route: ActivatedRoute, private router: Router) {
    this.selectedStock=[]
    this.pecentageAllocation=[]
    this.active=false;
    
  }



reload() {
  this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  this.router.onSameUrlNavigation = 'reload';
  this.router.navigate(['./'], { relativeTo: this.route });
}

  
  ngOnInit(): void {
    this.getPortfolioNameList()
    
  
    this.portfolioName= "Portfolio 1"
    this.psAllocation=0
    this.active=false
    
    this.selectedStock=[]
    this.stockNamesType=[]
    this.pecentageAllocation=[]
    
    this.getSectorList();

    this.MutualFundsSector();    
this.getBondsData();

    

  }
  
  getPortfolioNameList() {
    this.rebalanceService.getPortfolioNameList(1).subscribe(
      response => {
        this.portfolioNameList = response
        console.log(this.portfolioNameList)

        this.getInvestmentAmount();
        this.getRebStockNames();
        this.getRebStockPercent();
        this.getRebAllocAmount();
       // this.getStockClosePrice();
        
      }
    )
  }
  
  //Filter part
  getFilterStockList() {
    if(this.sectorDataRB != "ALL SECTORS")
    {
    this.rebalanceService.getFilterStockList(this.sectorDataRB).subscribe( 
      response => {
         this.stockListRB= response;        
        console.log(this.stockListRB) 
      }
    )
    }
    else{
      this.getAllSymbols();
    }
    
  }
  //Get all stocks names
  getAllSymbols() {
    this.rebalanceService.getAllAssetNames(this.sectorDataRB).subscribe( 
      response => {
        console.log(response)
         this.stockListRB= response;        
        console.log(this.stockListRB)
        
      }
    )
  } 
  
  //Fetch names of sectors
  getSectorList() {
    this.rebalanceService.getSectorList().subscribe(
      response => {
        this.sectorListRB= response;
        console.log(this.sectorListRB)
        
      }
    )
  }

//Mutual funds section and list
  MutualFundsSector() {
    this.rebalanceService.MutualFundsSector().subscribe(
      response  => {
        this.mutualFund = response
        console.log(this.mutualFund)
      }
    )
  }

  getMF_list()
  {
    this.rebalanceService.getMutualFundsList(this.mFund).subscribe(
      response => {
        this.mfList= response;
        console.log(this.mfList)
      }
    )
  }

  OnAdd_MF()
  {
   
    console.log( "units entered before - ",this.MF_psAllocation)
    this.getNavPrice().subscribe((NAV)=>{ 
      console.log(`LTP for ${this.stockName}`,NAV) ;
      
     this.MF_psAllocation = (this.MF_psAllocation*+NAV *100)/+this.invAmt ;

     console.log( "units entered in - ",this.MF_psAllocation)
      var runMethod=0
      console.log( "units-", this.MF_psAllocation )

      console.log(runMethod)
  
      if(this.MF_psAllocation<=0)
      {
        console.log("inside if")
        console.log(runMethod)
        runMethod=1
  
      window.alert("invalid Mutual Fund invest units allocation"); return
    }
  
    else if(this.MF_psAllocation > this.stockAllocationRemaining)
    {
      runMethod=1
      window.alert("Units amount can't be greater than Amount-allocation-remaining"); return
  
    }

    else if (this.stockAllocationRemaining===0) {
  
      runMethod=1
      window.alert("cannot add more Mutual funds");;
      return
  
    }
  
    if (runMethod===0) {
      console.log("inside if sdsd")
      
      this.stockNames.push(this.mfName)
      this.stockNamesType.push("mutualFund") ;
    }
    


      let NAV_Price = +(NAV[0])
      let CurAmt = (+this.invAmt * this.MF_psAllocation)/100;
      let Units = (CurAmt/NAV_Price);
      console.log( 'investmentAmount',+this.invAmt, 'psAllocation',this.MF_psAllocation,this.MF_psAllocation , 'curAmt',CurAmt,'\nUnits',Units)
      let UpdatedAmt = Units*NAV_Price;
      let UpdatedWeight = (UpdatedAmt/+this.invAmt)*100 ;
     
     
      this.stockPercent.push( UpdatedWeight.toString() ) ;
      this.allocationAmt.push(UpdatedAmt.toString());
      this.stockClosePrice.push(NAV[0]) ;
      this.stockAllocationRemaining=this.stockAllocationRemaining-UpdatedWeight ;
      this.allocationLeft -= UpdatedAmt

      this.stockName="";
      this.MF_psAllocation= 0 ;

    })
     

 
 

    console.log(this.selectedStock) ;
    console.log(this.pecentageAllocation) ;
    console.log(this.percentAmount) ;
  }

  getInvestmentAmount() {
    this.rebalanceService.getInvestmentAmount(this.portfolioName).subscribe(
        response => {
          this.invAmt = response
          console.log('investmentAmount is-' ,this.invAmt)

        }
    )
  }
  
  //Function to fetch stock names of a portfolio
  getRebStockNames() {
    this.rebalanceService.getRebalanceStockNames(this.portfolioName).subscribe(
      response => {
      this.stockNames = response
      console.log( 'stock Names-', this.stockNames)

     

      this.getStockClosePrice() ; 
      // this.pre_stockNames = this.stockNames.slice() ;
      
      for(let i=0;i<this.stockNames.length;i++) 
      { 
        if(this.stockNames[i].length<20)  this.stockNamesType[i]=("stock") ;
       else this.stockNamesType[i]=("mutualFund") ;
     }
      
      }
    )
  }
  
  getRebStockPercent() {
    this.rebalanceService.getRebalanceStockPercent(this.portfolioName).subscribe(
      response => {
        this.stockPercent = response
        console.log( 'Stock Percent is-', this.stockPercent)
        this.pre_stockPercent = this.stockPercent.slice()
  
        let stockPercentSum = 0;
        for(let i=0;i<this.stockPercent.length;i++)
        {      
           stockPercentSum+= +this.stockPercent[i] ;
         }  
         
         this.stockAllocationRemaining = 100-stockPercentSum ;
         this.allocationLeft = (this.stockAllocationRemaining* +this.invAmt)/100 ;
      }
    )
  }
  
  getRebAllocAmount() {
    this.rebalanceService.getRebalanceAllocAmount(this.portfolioName).subscribe(
      response => {
        this.allocationAmt = response
        console.log('allocation amt is', this.allocationAmt)
        this.pre_allocationAmt = this.allocationAmt.slice()
      }
    )
  }

   getStockClosePrice() {

    let stockClosingPriceList:any[]=[] ;

    

  for(let i=0;i<this.stockNames.length;i++)
  {
   
    if(this.stockNames[i].length < 20)
    this.rebalanceService.getStockClosePrice(this.stockNames[i]).subscribe(
      response => {
       // this.stockClosePrice = response
       stockClosingPriceList[i] = (+response) ;

      } ) 
      
    else {
       
       this.rebalanceService.getStockClPrice(this.stockNames[i]).subscribe(
        response => {
         // this.stockClosePrice = response
         stockClosingPriceList[i]= (+response) ;
         
        
        } ) }
  }
  this.stockClosePrice = stockClosingPriceList ;
  console.log( 'closing Prices-', stockClosingPriceList);
}
//Bond data selection and button functions  
  getBondsData() {
    this.rebalanceService.getBondsData(this.bondName).subscribe(
      response => {
        this.bondList = response
        console.log(this.bondList)
      }
    )
  }
  // Button function for bond data addition into table
  BondAdd() {
    var runMethod=0;
    console.log(this.Bond_value);
    console.log(runMethod)

    if(this.bondName == null || this.bondName == undefined)
    {
      runMethod = 1;
      window.alert("Select a bond name to invest in");
    }
     else if(this.Bond_value == null || this.Bond_value == undefined)
    {
      runMethod = 1;
      window.alert("Add number of units to invest");
    }

    else if(this.stockAllocationRemaining == 0)
    {
      runMethod = 1;
      window.alert("Increase investment amount to add bond data")
    }

    if(runMethod === 0){

    }
  }

    
  

  getStockClPrice() {
    return this.rebalanceService.getStockClosePrice(this.stockName)
  }
  
  getNavPrice() {
    return this.rebalanceService.getStockClPrice(this.mfName)
  }
  

  addStock() {
  
   

   
  
    this.getStockClPrice().subscribe((LTP)=>{ 
      console.log(`LTP for ${this.stockName}`,LTP) ;
      
      var    runMethod=0
      console.log( "Units in", this.psAllocation)
    console.log(runMethod)

 
    this.psAllocation = (this.psAllocation* (+LTP)*100)/+this.invAmt;

    //------
    
    console.log("psAlloaction ",this.psAllocation,"Percent-Alloc-Remaining ",this.stockAllocationRemaining, "invt amt- ", this.invAmt)
 
 //---- 
      if(this.psAllocation<=0)
      {
        console.log("inside if")
        console.log(runMethod)
        runMethod=1
    
      window.alert("Units should be Positive Integer");
      this.psAllocation=0 ;
      return;
    }
    
    else if(this.psAllocation > this.stockAllocationRemaining)
    {
      runMethod=1
      window.alert("Units amount can't be greater than Stock Allocation Remaining")
      console.log("psAlloaction ",this.psAllocation,"Percent-Alloc-Remaining ",this.stockAllocationRemaining)
      this.psAllocation=0 ;
      return;
    
    }
    
    
    else if (this.stockAllocationRemaining===0) {
    
      runMethod=1
      window.alert("cannot add more stocks") ;
      this.psAllocation=0 ;
      return;
    
    }
    
    if (runMethod===0) {
    
     // console.log("Read sector data")
      // this.stockAllocationRemaining=this.stockAllocationRemaining-this.psAllocation;
     
      
      
  

      let LTP_Price = +(LTP[0]) 
      let CurAmt = (+this.invAmt * this.psAllocation)/100 ;
      let Units = Math.floor(CurAmt/LTP_Price) ; 
      console.log( 'investmentAmount',+this.invAmt, 'psAllocation',this.psAllocation,this.pecentageAllocation , 'curAmt',CurAmt,'\nUnits',Units)
      let UpdatedAmt = Units*LTP_Price;
      let UpdatedWeight = (UpdatedAmt/+this.invAmt)*100 ;
       if(Units==0)
       {
         this.psAllocation=0 ;
             window.alert("Units cannot be zero") ;
              return ;
       }
      
      this.stockNamesType.push("stock")
      this.stockNames.push(this.stockName)
      this.stockPercent.push( UpdatedWeight.toString() );
      this.allocationAmt.push(UpdatedAmt.toString());
      this.stockClosePrice.push( LTP[0] )
      this.stockAllocationRemaining=this.stockAllocationRemaining-UpdatedWeight
      this.allocationLeft -= UpdatedAmt

      this.stockName="" ;
    this.psAllocation=0 ;

    }})

    console.log(this.stockPercent)
    console.log(this.allocationAmt)
    }

    


  
  
  //Update function for investment amount
     updateStockDetails() {
  
        console.log("Previous Invested Amount- ",this.invAmt);
        console.log("Total Invested Amount(Updated)- ",this.investmentAmount);
        
        this.Cur_investmentAmount = this.investmentAmount ;
        this.selectedStock = [] ;
        this.pecentageAllocation = [] ;
  
        
      //   if( (+this.invAmt) == this.investmentAmount)
      //   {  
      //     alert('New Invested Amount is same as previous')
      //        return;
      //       // this.stockPercent = this.org_stockPercent ;
      //       // this.allocationAmt = this.org_allocationAmt ;
      //       // this.stockAllocationRemaining = 0 ;
      //   }
  
      //  else if((+this.invAmt) > this.investmentAmount)
      //   {
      //     for(let i=0;i<this.stockPercent.length;i++)
      //     {
      //       this.allocationAmt[i] = ((this.investmentAmount* (+this.stockPercent[i]) )/100).toString()
      //     }
           
      //   }
        if(this.investmentAmount <= 0 || this.investmentAmount == undefined)
        {
           alert('Invalid investment amount') ;return
        }

        else 
        {
          this.investmentAmount = (+this.invAmt) + this.investmentAmount ;
          this.invAmt = [this.investmentAmount.toString()] ;
              let stockPercentSum = 0.0;
           for(let i=0;i<this.stockPercent.length;i++)
           {
              this.stockPercent[i] = ((+this.allocationAmt[i] *100 / this.investmentAmount )).toString() ; 
              stockPercentSum+= +this.stockPercent[i] ;
           }  
            
            this.stockAllocationRemaining = 100-stockPercentSum ;
            this.allocationLeft = (this.stockAllocationRemaining * this.investmentAmount) /100;
        }

        
        //this.pre_closePrice = this.stockClosePrice.slice() ;
        this.pre_allocationAmt = this.allocationAmt.slice() ;
        this.pre_stockPercent = this.stockPercent.slice() ;
        this.pre_stockNames = this.stockNames.slice()
        this.pre_stockAllocationRemaining = this.stockAllocationRemaining ;
        this.invAmt = [this.investmentAmount.toString()] ;

        this.investmentAmount=0
  }
  
  ResetStockDetails()
  {
    console.log(this.stockNames, this.pre_stockNames )
  
     this.stockPercent = this.pre_stockPercent.slice() ;
     this.allocationAmt = this.pre_allocationAmt.slice() ;
     this.stockNames = this.pre_stockNames.slice() 
     this.stockClosePrice = this.org_stockPrice.slice() ;
     this.stockAllocationRemaining = this.pre_stockAllocationRemaining ;
     
  }

  OnRemoveStock(idx:number){
    this.stockNames.splice(idx,1);

    this.stockNamesType.splice(idx,1) ;

    this.stockAllocationRemaining+= +this.stockPercent[idx];
    this.allocationLeft = (this.stockAllocationRemaining * +this.invAmt) / 100;
    this.stockPercent.splice(idx,1);
    this.allocationAmt.splice(idx,1) ;
    this.stockClosePrice.splice(idx,1);
    
  }

  UnitsAdd(ind:number) {
    
      if( this.allocationLeft - +this.stockClosePrice[ind] < 0 ) {
        alert( "Investment Remaining is lesser then item Price " ) ;return;
      }

      this.pre_allocationAmt = this.allocationAmt.slice()
      let stockUnits = (+this.allocationAmt[ind] / +this.stockClosePrice[ind]);
      stockUnits = stockUnits + 1;
      this.allocationAmt[ind] = (+this.stockClosePrice[ind] * stockUnits).toString()
      this.stockPercent[ind] = ((+this.stockPercent[ind] * +this.allocationAmt[ind]) / +this.pre_allocationAmt[ind]).toString()
     
      this.allocationLeft -= +this.stockClosePrice[ind];
  
    }

  UnitsMinus(id:number) {
        this.pre_allocationAmt = this.allocationAmt.slice()
        let stockUnits = Math.floor(+this.allocationAmt[id] / +this.stockClosePrice[id]);
        if(stockUnits == 1)
        {
          window.alert("Can't reduce more stock units") ; return;
        }
        else
        {
          stockUnits = stockUnits - 1;
        } 

        this.allocationAmt[id] = (+this.stockClosePrice[id] * stockUnits).toString()
        this.stockPercent[id] = ((+this.stockPercent[id] * +this.allocationAmt[id]) / +this.pre_allocationAmt[id]).toString()
        
        this.allocationLeft += +this.stockClosePrice[id];

  }

   UpdatePortFolioDB()
  {
    let runMethod=1;

     this.selectedStock = this.stockNames ;
     this.selectedStockType = this.stockNamesType ;
      
     let actualInvestmentAmount=0.0;
     this.allocationAmt.forEach( (a)=>{actualInvestmentAmount += +a} ) ;

    for(let i=0;i<this.stockPercent.length;i++)
    {
        this.stockPercent[i] = ((+this.allocationAmt[i]*100)/actualInvestmentAmount).toString() ;
    }

     
    if (runMethod === 1) {
      var j = new PortfolioInfo(
        '',
        -1,
        this.portfolioName,
        actualInvestmentAmount
      );
      
      var obj1 = new StockList(
        this.stockNames,
        this.stockNamesType,
        this.stockPercent.map(Number),
        this.portfolioName
      
        );

      var obj = new hh(j, obj1) ;
      console.log("porfolio ibj,StockListobj at craete portfolio");
      console.log(j)
      console.log(obj1)
      console.log(obj)

      // delete data and insert updataed data with same portfolio_id  
    this.rebalanceService.delete_insert_PortFolio(obj).subscribe((response) => {
      console.log('res of rebalace portf- ', response) ;

      window.alert( "Portfolio Updated Successfully" ) ;
      
      this.reload()
      
      
       
    }); 
    
 
 
     

      // this.createPortfolioService
      //   .addStockListAndPercentAllocation(obj)
      //   .subscribe((response) => {
      //     console.log('res of craete portf- ', response) ;
      //     this.message = 'Portfolio created successfully';
      //     this.active = true;
      //     this.form.reset();
      //     this.isReadyToCreate = false;
      //   });
      //   window.alert("Portfolio created with name :" + this.portfolioName)

      // this.abc()
    }

  }
  
  }