import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { hh, PortfolioInfo, StockList } from 'src/app/create-portfolio/create-portfolio.component';

@Injectable({
  providedIn: 'root'
})
export class CreateServiceService {

  constructor(public http:HttpClient) { }

  // getStockList() {
  //   return this.http.get<string[]>(`http://localhost:8080/create/stock/getAllStockName/{industry}`)
  // }

  //To filter the stocks based on selection
  getFilterStockList(industry:string) {
    return this.http.get<string[]>(`http://localhost:8080/create/stock/getAllStockName/${industry}`)
  }

  //To display all stock names
  getAllAssetNames(industry:string) {
    return this.http.get<string[]>(`http://localhost:8080/create/sect/getAllAssetNames/${industry}`)
  }

  getSectorList() {
    return this.http.get<string[]>(`http://localhost:8080/create/sector/getAllSectorData`)
  }

   
  addNewPortfolio(portfolioInfo:PortfolioInfo) {
    console.log(portfolioInfo)
    return this.http.post<void>("http://localhost:8080/create/add/newPortfolio",portfolioInfo)
  }

  getPortfolioId(name:string) {
    return this.http.get<string>(`http://localhost:8080/home/porfolioId/${name}`)
  }

  
  addStockListAndPercentAllocation(selectedStock:hh) {
    return this.http.post<void>("http://localhost:8080/create/add/add2", selectedStock )
  }

  getMutualFundsList(mFund:string){
    return this.http.get<string[]>(`http://localhost:8080/create/mutualFundsList/${mFund}`)
    }

    MutualFundsSector(){
      return this.http.get<string[]>(`http://localhost:8080/create/mutualSectionList/`)
      }
  

    getStockClosePrice(stockName:string) {
      return this.http.get<string[]>(`http://localhost:8080/create/getLTP/${stockName}`)
    }

  getNav(mfName:string){
    return this.http.get<string[]>(`http://localhost:8080/create/getNav/${mfName}`)
  }
  
}
