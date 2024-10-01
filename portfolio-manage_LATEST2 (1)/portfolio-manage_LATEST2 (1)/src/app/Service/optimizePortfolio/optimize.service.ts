import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AssetWeights } from 'src/app/home-page/home-page.component';
import { ChartData, LineHelper } from 'src/app/optimize-portfolio/optimize-portfolio.component';

@Injectable({
  providedIn: 'root'
})
export class OptimizeService {

  constructor(public http:HttpClient) { }

  getPortfolioNameList(id:any) {
    return this.http.get<string[]>(`http://localhost:8080/home/porfolioName/${id}`)
  }

  getLineChartData(id:any) {
    return this.http.get<LineHelper>(`http://localhost:8080/home/chart/chartData/${id}`)
  }
}
