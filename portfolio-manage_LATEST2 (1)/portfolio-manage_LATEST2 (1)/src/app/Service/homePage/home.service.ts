import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StockList } from 'src/app/create-portfolio/create-portfolio.component';
import { AssetWeights, Portfolio_Performance, SectorData, SectorWiseCount } from 'src/app/home-page/home-page.component';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(public http:HttpClient) { }
  
  getPortfolioNameList(id:any) {
    return this.http.get<string[]>(`http://localhost:8080/home/porfolioName/${id}`)
  }

  getAssetWeightsData(id:any) {
    return this.http.get<AssetWeights[]>(`http://localhost:8080/home/chart/assetWeights/${id}`)
  } 

  getPortfolioPerformance(id:string) {
    return this.http.get<Portfolio_Performance>(`http://localhost:8080/home/portfolioPerformance/${id}`)
  }

  getStockList(portfolioName:string) {
    return this.http.get<string[]>(`http://localhost:8080/home/stock/getStockList/${portfolioName}`)
  }

  getSectorNameList(portfolioName:string){
    return this.http.get<string[]>(`http://localhost:8080/home/sect/getSelectedSector/${portfolioName}`)
  }

  getSectorData(id:any) {
    return this.http.get<SectorWiseCount[]>(`http://localhost:8080/home/chart/sectorData/${id}`)
  }
}