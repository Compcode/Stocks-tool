import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PortfolioPrediction } from 'src/app/portfolio-prediction/portfolio-prediction.component';

@Injectable({
  providedIn: 'root'
})
export class PortFoiloPredictionService {

  constructor( public http:HttpClient ) { }

  getPredictionValue(id:any) {
    return this.http.get<PortfolioPrediction[]>(`http://localhost:8080/portfolioPredction/predValues/${id}`)
  }

  getPortfolioName(id:any) {
    return this.http.get<string[]>(`http://localhost:8080/home/porfolioName/${id}`)
  }

  getPortfolioId(name:any) {
    return this.http.get<string>(`http://localhost:8080/home/porfolioId/${name}`)
  }
}
