import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';


import { observable, Observable } from 'rxjs';
import { AssetWeights } from '../home-page/home-page.component';
import { CreateServiceService } from '../Service/createPortfolio/create-service.service';

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
  selector: 'app-create-portfolio',
  templateUrl: './create-portfolio.component.html',
  styleUrls: ['./create-portfolio.component.css'],
})
export class CreatePortfolioComponent implements OnInit {
  @ViewChild('f') form: NgForm;

  UpdateModelMessage: string;

  isReadyToCreate: boolean = false;

  mFund: string;
  mutualFund: string[];
  mfName: string ;
  mfList: string[] ;

  portfolioName: string;
  investmentAmount: number;
  userId: number = 1;
  addAll: any[];

  sectorList: string[];
  sectorData: string;

  //stockList is different from the one in the StockList class, this is for the dropdown list to fetch sectors
  stockList: string[];
  stockName: string;

  nav: string[];
  stockClosePrice: string[];

  psAllocation: number;
  MF_psAllocation: number;
  percent: number;

  active: boolean;
  message: string;
  selectedSector: string;

  selectedStock: string[];
  selectedStockType: string[];
  selectedStockLTP_NAV: string[];
  pecentageAllocation: number[];
  toShow_pecentageAllocation: number[];
  percentAmount: number[] ;

  totalStockAllocation!: number;
  stockAllocationRemaining: number;

  portfolioInfo!: PortfolioInfo;
  portId: string;

  constructor(private createPortfolioService: CreateServiceService) {
    this.selectedSector = '';
    this.selectedStock = [];
    this.pecentageAllocation = [];
    this.percentAmount = [];
    this.active = false;
  }

  ngOnInit(): void {
    this.portfolioName = '';
    this.investmentAmount;
    this.userId = 1;
    this.addAll = [];
    this.sectorList = [];
    this.selectedStockLTP_NAV = [];

    this.UpdateModelMessage = '-1';
    this.isReadyToCreate = false;
    this.stockList = [];
    this.mfList = [];

    this.stockName = '';
    this.mfName = '';
    // this.psAllocation=0

    this.active = false;
    this.message = '';

    this.selectedSector = '';

    this.selectedStock = [];
    this.selectedStockType=[] ;
    this.pecentageAllocation = [];
    this.toShow_pecentageAllocation =  this.pecentageAllocation ;

    this.stockAllocationRemaining = 100;

    this.portId = '';

    this.getSectorList();

    this.MutualFundsSector();
  }

  //Fetch names of sectors
  getSectorList() {
    this.createPortfolioService.getSectorList().subscribe((Response) => {
      console.log(Response);
      this.sectorList = Response;
      console.log(this.sectorList);
    });
  }

  getFilterStockList() {
    if (this.sectorData != 'ALL SECTORS') {
      this.createPortfolioService
        .getFilterStockList(this.sectorData)
        .subscribe((response) => {
          this.stockList = response;
          console.log(this.stockList);
        }) ;
    } else {
      this.getAllAssets();
    }
  }

  //Mutual funds section and list
  MutualFundsSector() {
    this.createPortfolioService.MutualFundsSector().subscribe((response) => {
      this.mutualFund = response;
      console.log( 'fund Sectors-', this.mutualFund);
    });
  }

  getMF_list() {
    this.createPortfolioService.getMutualFundsList(this.mFund).subscribe((response) => {
      this.mfList = response;
      console.log(this.mfList);
    });
  }
  //Get all stocks names
  getAllAssets() {
    this.createPortfolioService
      .getAllAssetNames(this.sectorData)
      .subscribe((response) => {
        //  console.log(response)
        this.stockList = response;
        console.log('StockList is-> ', this.stockList);
      });
  }

  getStockClosePrice() {
    return this.createPortfolioService.getStockClosePrice(this.stockName);
  }

  getNav() {
    return this.createPortfolioService.getNav(this.mfName);
  }

  OnAdd_MF() {

 this.getNav().subscribe((NAV) => {
        console.log(`LTP for ${this.mfName}`, NAV);

        let NAV_Price = +NAV[0];
        console.log('Before chnage MF_psLLoaction is - ',this.MF_psAllocation);
        this.MF_psAllocation = ((NAV_Price*this.MF_psAllocation)/this.investmentAmount)*100;

        var runMethod = 0;
    console.log('After chnages MF_psLLoaction is - ',this.MF_psAllocation);

    console.log(runMethod);

    if (this.MF_psAllocation <= 0 ) {
      console.log('inside if');
      console.log(runMethod);
      runMethod = 1;

      window.alert('invalid Mutual Fund invest units allocation');
    } else if (this.MF_psAllocation > this.stockAllocationRemaining) {
      runMethod = 1;
      window.alert(
        "units amount allocation can't be greater than Amount Allocation-Remaining"
      );
    } else if (this.stockName == undefined) {
      runMethod = 1;
      window.alert("Mutual Fund name can't be empty");
    } else if (this.stockAllocationRemaining === 0) {
      runMethod = 1;
      window.alert('cannot add more Mutual funds');
    }

    if (runMethod === 0) {
      console.log('inside if sdsd');


        let CurAmt = (this.investmentAmount * this.MF_psAllocation) / 100;
        let Units = (CurAmt / NAV_Price);
        console.log(
          'investmentAmount',
          this.investmentAmount,
          'psAllocation',
          this.MF_psAllocation,
          this.MF_psAllocation,
          'curAmt',
          CurAmt,
          '\nUnits',
          Units
        );
        let UpdatedAmt = Units * NAV_Price;
        let UpdatedWeight = (UpdatedAmt / this.investmentAmount) * 100;

        this.selectedStock.push(this.mfName);
        this.selectedStockType.push("mutualFund");
        this.pecentageAllocation.push(UpdatedWeight);
        this.percentAmount.push(UpdatedAmt);
        this.selectedStockLTP_NAV.push(NAV[0]);
        this.stockAllocationRemaining =
          this.stockAllocationRemaining - UpdatedWeight;

        this.mfName = '';
        this.MF_psAllocation = 0;

        console.log(this.selectedStock);
        console.log(this.selectedStockType);
        console.log(this.pecentageAllocation);
        console.log(this.percentAmount);
      }});
    

  
  }

  // psAllocation is no of Units Now 
  addStock(Cur_psAllocation:number = this.psAllocation) {
    

      // this.pecentageAllocation.push(this.psAllocation)
      // this.percent = (this.investmentAmount * this.psAllocation)/100;
      // this.percentAmount.push(this.percent)
      //  let psAllocation= this.psAllocation
 this.psAllocation =0;
      this.getStockClosePrice().subscribe((LTP) => {
        console.log(`LTP for ${this.stockName}`, LTP);

        let LTP_Price = +LTP[0] ;

        var runMethod = 0;
  
    Cur_psAllocation = ((LTP_Price*Cur_psAllocation)/this.investmentAmount)*100 ;

    console.log('pecetage Allocation',Cur_psAllocation);
    console.log(runMethod);

    if (Cur_psAllocation <= 0 ) {
      console.log('inside if');
      console.log(runMethod);
      runMethod = 1;

      window.alert('invalid Units allocation');
    } else if (Cur_psAllocation > this.stockAllocationRemaining) {
      runMethod = 1;
      window.alert(
        "units amount allocation can't be greater than amount-Allocation-Remaining"
      );
    } else if (this.stockName == undefined) {
      runMethod = 1;
      window.alert('stock name cant be empty');
    } else if (this.stockAllocationRemaining === 0) {
      runMethod = 1;
      window.alert('cannot add more stocks');
    }

    if (runMethod === 0) {
      console.log('inside if sdsd');

      console.log(
        'This stock name to select is ',
        this.stockName,
        ',\n',
        this.selectedSector
      );

        let CurAmt = (this.investmentAmount * Cur_psAllocation) / 100;
        let Units = (CurAmt / LTP_Price);

        console.log(
          'StockName',
          this.stockName,
          'investmentAmount',
          this.investmentAmount,
          'psAllocation',
          Cur_psAllocation,
          this.pecentageAllocation,
          'curAmt',
          CurAmt,
          '\nUnits',
          Units
        );
        let UpdatedAmt = Units * LTP_Price;
        let UpdatedWeight = (UpdatedAmt / this.investmentAmount) * 100;

        console.log('StockName len', this.stockName.length);

        if (Units == 0) {
          alert('Stock Price is greater then Remaining amount');
          return;
        }

        this.selectedStock.push(this.stockName);
        this.selectedStockType.push("stock");
        this.pecentageAllocation.push(UpdatedWeight);
        this.percentAmount.push(UpdatedAmt);
        this.selectedStockLTP_NAV.push(LTP[0]);
        this.stockAllocationRemaining =
          this.stockAllocationRemaining - UpdatedWeight;
        this.stockName = '';
        Cur_psAllocation = 0;

        console.log(this.selectedStock);
        console.log(this.selectedStockType);
        console.log(this.pecentageAllocation);
        console.log(this.percentAmount);

        console.log('toShow_pecentageAllocation-',this.toShow_pecentageAllocation);
      } 
    });
    

   
  }
  

  OnRemoveStock(idx: number) {
    this.selectedStock.splice(idx, 1);
    this.percentAmount.splice(idx, 1);
    this.stockAllocationRemaining += this.pecentageAllocation[idx];
    this.pecentageAllocation.splice(idx, 1);
    this.selectedStockLTP_NAV.splice(idx, 1);
  }

  //method to update amount when allocated to stock out of 100
  dropSkill(index: any) {
    this.selectedStock.splice(index, 1);

    var pa = this.pecentageAllocation.splice(index, 1);
    this.stockAllocationRemaining = this.stockAllocationRemaining + pa[0];

    console.log('---------');
    console.log(pa);
    console.log('---------');
    console.log(this.stockName);
    console.log(this.pecentageAllocation);
  }

  submitPortfolio() {
    console.log('--------------------------------------');
    console.log('submit portfolio');
    console.log(this.portfolioName);

    console.log(this.userId);
    console.log(this.investmentAmount);

    var j = new PortfolioInfo(
      '',
      this.userId,
      this.portfolioName,
      this.investmentAmount
    );

    console.log(j);
    console.log(j.user_id);
    console.log(j.portfolio_id);
    console.log(j.portfolio_name);
    console.log(j.portfolio_investment);

    this.createPortfolioService.addNewPortfolio(j).subscribe((response) => {
      console.log(response);

      //  this.stockList= response;

      // console.log(this.stockList)
    });

    console.log('--------------------------------------');
  }

  getPortfolioId() {
    console.log('-----getAllportfolioName-----');
    this.createPortfolioService.getPortfolioId(this.portfolioName).subscribe(
      (response) => {
        console.log(response);

        this.portId = response;

        console.log(`this is port id -${this.portId}`);
      },

      (error) => this.handleErrorResponse1(error)
    );

    console.log('--------------------------------------');
  }

  handleErrorResponse1(error: any) {
    console.log('this is error message ');
    console.log(error);
    console.log(error.error);
    console.log(error.error.message);
    console.log(error.error.text);
    this.portId = error.error.text;
  }

  submitPortfolio1() {
    var runMethod = 1;

    if (
      this.portfolioName === undefined ||
      this.portfolioName === '' ||
      this.portfolioName === null
    ) {
      runMethod = 0;
      window.alert('Invalid Portfolio Name');
      return;
    }

    if (
      this.investmentAmount === undefined ||
      this.investmentAmount === 0 ||
      this.investmentAmount === null
    ) {
      runMethod = 0;
      window.alert('Invalid investment amount');
      return;
    }

    if (
      this.selectedStock === undefined ||
      this.selectedStock.length === 0 ||
      this.selectedStock === null ||
      this.pecentageAllocation === undefined ||
      this.pecentageAllocation.length === 0 ||
      this.pecentageAllocation === null
    ) {
      runMethod = 0;
      window.alert('select atleast 1 stock');
      return;
    }
 
    let realInvestmentAmount:number = 0.0 ;
     for(let i=0;i<this.percentAmount.length;i++){
       realInvestmentAmount+= this.percentAmount[i]
     }
    

    if (runMethod === 1) {
      var j = new PortfolioInfo(
        '',
        this.userId,
        this.portfolioName,
        // this.investmentAmount
        realInvestmentAmount
      );
      var obj1 = new StockList(
        this.selectedStock,
        this.selectedStockType,
        this.toShow_pecentageAllocation,
        this.portfolioName
      
        );
      var obj = new hh(j, obj1);
      console.log("porfolio ibj,StockListobj at craete portfolio");
      console.log(j)
      console.log(obj1)
      console.log(obj)

      

      this.createPortfolioService
        .addStockListAndPercentAllocation(obj)
        .subscribe((response) => {
          console.log('res of craete portf- ', response) ;
          this.message = 'Portfolio created successfully';
          this.active = true;
          this.form.reset();
          this.isReadyToCreate = false;
        });
      // window.alert("Portfolio created with name :" + this.portfolioName)

     //  this.abc()

      this.stockAllocationRemaining = 0 ;
      this.investmentAmount=0
    }

    (async () => {
      // Do something before delay

      console.log('before delay');
      await this.delay(4000);

      // location.reload();

      this.active = false;

      console.log('after delay');

      this.ngOnInit();
    })();
  }

  updateWeight() {
    var isValid = 1;

    if (
      this.portfolioName === undefined ||
      this.portfolioName === '' ||
      this.portfolioName === null
    ) {
      this.UpdateModelMessage = 'Invalid Portfolio Name';
      isValid = 0;
    } else if (
      this.investmentAmount === undefined ||
      this.investmentAmount === 0 ||
      this.investmentAmount === null
    ) {
      this.UpdateModelMessage = 'Invalid investment amount';
      isValid = 0;
    } else if (
      this.selectedStock === undefined ||
      this.selectedStock.length === 0 ||
      this.selectedStock === null ||
      this.pecentageAllocation === undefined ||
      this.pecentageAllocation.length === 0 ||
      this.pecentageAllocation === null
    ) {
      this.UpdateModelMessage = 'select atleast 1 stock';
      isValid = 0;
    }

    if (isValid == 1) {
      let updateWeightsList: number[] = [];

      let weightSum = 0;
      for (let i = 0; i < this.pecentageAllocation.length; i++)
        weightSum += this.pecentageAllocation[i];

      for (let i = 0; i < this.pecentageAllocation.length; i++)
        updateWeightsList.push((this.pecentageAllocation[i] / weightSum) * 100);

      this.toShow_pecentageAllocation = updateWeightsList.slice();
      this.UpdateModelMessage =
        'Weights Upadated Succesfully and Ready to Create PortFolio ';

     
      this.stockAllocationRemaining = 0;
      this.isReadyToCreate = true;
    }
    
  }

  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
}
