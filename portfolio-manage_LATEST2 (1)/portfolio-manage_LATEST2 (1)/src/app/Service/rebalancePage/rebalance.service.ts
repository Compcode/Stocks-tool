import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { hh } from 'src/app/create-portfolio/create-portfolio.component';

import { AssetWeights } from 'src/app/home-page/home-page.component';

@Injectable({
    providedIn: 'root'
  })
export class RebalanceService {
  constructor(public http:HttpClient) { }

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

  getPortfolioNameList(id:any) {
    return this.http.get<string[]>(`http://localhost:8080/home/porfolioName/${id}`)
  }

  getInvestmentAmount(id:string) {
    return this.http.get<string[]>(`http://localhost:8080/rebalance/investment/getInvestmentAmount/${id}`)
  }

  getRebalanceStockNames(stkNames:string) {
    return this.http.get<string[]>(`http://localhost:8080/rebalance/stocks/getRebStockNames/${stkNames}`)
  }

  getRebalanceStockPercent(stkPercent:string) {
    return this.http.get<string[]>(`http://localhost:8080/rebalance/percent/getRebStockPercent/${stkPercent}`)
  }

  getRebalanceAllocAmount(allocAmount:string) {
    return this.http.get<string[]>(`http://localhost:8080/rebalance/allocation/getRebAllocAmount/${allocAmount}`)
  }

  getStockClosePrice(stockClosePrice:string) {
    return this.http.get<string[]>(`http://localhost:8080/create/getLTP/${stockClosePrice}`)
  }

  getStockClPrice(stockName:string) {
    return this.http.get<string[]>(`http://localhost:8080/create/getNav/${stockName}`)
  }

//Mutual funds sector list dropdown along with mutual funds list below
  MutualFundsSector() {
    return this.http.get<string[]>(`http://localhost:8080/create/mutualSectionList/`)
  }

  getMutualFundsList(mutualFundsData:string){
    return this.http.get<string[]>(`http://localhost:8080/create/mutualFundsList/${mutualFundsData}`)
  }

  delete_insert_PortFolio(portfolio:hh)
  {
     return this.http.post<string[]>("http://localhost:8080/rebalance/deletePortfolio",portfolio) ;
  }

 getBondsData(bondNames:string) {
    return this.http.get<string[]>(`http://localhost:8080/rebalance/getBondNames/${bondNames}`)
  }
}