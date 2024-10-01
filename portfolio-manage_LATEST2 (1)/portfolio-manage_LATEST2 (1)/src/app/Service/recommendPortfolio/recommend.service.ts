import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AssetWeights, Portfolio_Performance } from 'src/app/home-page/home-page.component';
import { SectorWiseStockCount } from 'src/app/recommended-portfolio/recommended-portfolio.component';

@Injectable({
  providedIn: 'root'
})
export class RecommendService {

  constructor(public http:HttpClient) { }

  getPortfolioNameList(id:any) {
    return this.http.get<string[]>(`http://localhost:8080/home/recommendedPorfolioName/${id}`)
  }

  getPortfolioPerformance(id:string) {
    return this.http.get<Portfolio_Performance>(`http://localhost:8080/home/recommendedPortfolioPerformance/${id}`)
  }

  getAssetWeightsData(id:any) {
    return this.http.get<AssetWeights[]>(`http://localhost:8080/home/chart/recommendedAssetWeights/${id}`)
  }

//Fetch sector data in the list on the left
  getStockList(portfolioName:string) {
    return this.http.get<string[]>(`http://localhost:8080/Recom/getRStockCount/${portfolioName}`)
  }

  getRecSectorChartData(id:any) {
    return this.http.get<SectorWiseStockCount[]>(`http://localhost:8080/chart/RSectorData/${id}`)
  }

  getSectorStockList(portfolioName:string) {
    return this.http.get<string[]>(`http://localhost:8080/recommend/stock/getStockList/${portfolioName}`)
  }
}
