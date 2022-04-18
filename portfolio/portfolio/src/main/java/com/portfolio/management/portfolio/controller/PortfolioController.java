package com.portfolio.management.portfolio.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.management.portfolio.model.AssetWeights;
import com.portfolio.management.portfolio.model.ChartData;
import com.portfolio.management.portfolio.model.PortfolioInfo;
import com.portfolio.management.portfolio.model.PortfolioPrediction;
import com.portfolio.management.portfolio.model.Portfolio_Performance;
import com.portfolio.management.portfolio.model.RecommendedAssetWeights;
import com.portfolio.management.portfolio.model.RecommendedPortPerformance;
import com.portfolio.management.portfolio.repository.SectorDataRepo;
import com.portfolio.management.portfolio.repository.AssetDataRepo;
import com.portfolio.management.portfolio.repository.AssetInfoRepo;
import com.portfolio.management.portfolio.repository.AssetWeightRepo;
import com.portfolio.management.portfolio.repository.ChartDataRepo;
import com.portfolio.management.portfolio.repository.PortfolioInfoRepo;
import com.portfolio.management.portfolio.repository.PortfolioPerformanceRepo;
import com.portfolio.management.portfolio.repository.PortfolioPredictionRepo;
import com.portfolio.management.portfolio.repository.RebalanceAssetRepo;
import com.portfolio.management.portfolio.repository.RecommendedAssetWeightsRepo;
import com.portfolio.management.portfolio.repository.RecommendedPortPerformanceRepo;
import com.portfolio.management.portfolio.repository.SectorDataRepo;

import flask.PostClass;
import helper.Hh;
import helper.LineHelper;
import helper.RecSectorWiseStockCount;
import helper.SectorWiseStockCount;
import helper.StockList;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:56093")
public class PortfolioController {

		@Autowired
		PortfolioPerformanceRepo umr;
		
		@Autowired
		AssetDataRepo adr;
		
		@Autowired
		AssetWeightRepo awr;
		
		@Autowired
		PortfolioPredictionRepo ppr;
		
		@Autowired
		PortfolioInfoRepo pir;
		
		@Autowired
		ChartDataRepo cdr;
		
		@Autowired
		AssetInfoRepo air;
		
		@Autowired
		PortfolioPerformanceRepo pprr;
		
		@Autowired
		RecommendedPortPerformanceRepo rpp;
		
		@Autowired
		RecommendedAssetWeightsRepo raw;
		
		@Autowired
		SectorDataRepo sdr;
		
		@Autowired
		RebalanceAssetRepo rar;

//Fetch sector data from db
		@GetMapping("/create/sectordata/getAllSectorData")
		public List<String> getSectorData() {
			System.out.println("====================getSectorData================");
			System.out.println(sdr.getSectorData("sectordata"));
			 
			System.out.println("=================================================");
			return (List<String>) sdr.getSectorData("sectordata");
		}
		
//Filter part for stock names
		@GetMapping("/create/stock/getAllStockName/{industry}")
        public List<String> getAllStockName(@PathVariable String industry) {
                        System.out.println("====================getALLStockList=====================");
                        //System.out.println(sdr.getSectorInfo("stock"));
                        System.out.println("========================================================");
                        return (List<String>) sdr.getSectorInfo(industry);
        }
        
        
 //Get list of sector names      
        @GetMapping("/create/sector/getAllSectorData")
        public List<String> getAllSectorData() {
                        System.out.println("====================getALLSectorStockList=====================");
                        System.out.println(sdr.getSectorData("sector"));
                        System.out.println("========================================================");
                        return (List<String>) sdr.getSectorData("sector");
        }
        
 //Show all stocks data when ALL STOCKS are selected
        @GetMapping("/create/sect/getAllAssetNames/{industry}")
        public List<String> getAllSymbolNames(@PathVariable String industry) {
                        System.out.println("====================getALLSectorAssetNames=====================");
                        System.out.println("========================================================");
                        return (List<String>) sdr.getAllSectorInfo(industry);
        } 
		
		
//Rebalance portfolio 
        //amount fetching
		@GetMapping("/rebalance/investment/getInvestmentAmount/{portfolioName}")
				public double getInvestment(@PathVariable String portfolioName) {
				System.out.println("====================getInvestmentAmount=====================");
				String portID = pir.getPortfolioId(portfolioName);
				return (double) pir.getPortfolioInvestment(portID);
		}
		
		//stock names and their inv percentage
		@GetMapping("/rebalance/stocks/getRebStockNames/{stkNames}")
				public List<String> getAllRebStocks(@PathVariable String stkNames) {
				System.out.println("=================getRebalanceStockNames====================");
				String portfID = pir.getPortfolioId(stkNames);
				return (List<String>) rar.getRebStockNames(portfID);
		}
		
		@GetMapping("/rebalance/percent/getRebStockPercent/{stkPercent}")
				public List<String> getAllRebStocksPercent(@PathVariable String stkPercent) {
				System.out.println("=================getRebalanceStockNames====================");
				String portfID = pir.getPortfolioId(stkPercent);
				return (List<String>) rar.getRebStockPercent(portfID);
		}
				
		@GetMapping("/rebalance/allocation/getRebAllocAmount/{allocAmount}")
				public List<String> getAllRebAllocAmounts(@PathVariable String allocAmount) {
				System.out.println("=================getRebalanceStockNames====================");
				String portfID = pir.getPortfolioId(allocAmount);
				return (List<String>) rar.getRebAllocAmount(portfID);
		}
		
		@GetMapping("/rebalance/stockClosingPrice/getLTPrice/{stockClosePrice}")
		public List<String> getAllstockClosingPrice(@PathVariable String stockClosePrice) {
			System.out.println("=============================getAllstockClosingPrice==================");
			String portID = pir.getPortfolioId(stockClosePrice);
			return (List<String>) adr.getAllstockClosingPrice(portID);
		}
//Rebalance ends here
		
		
		@PostMapping("/create/add/newPortfolio")
			public void addNewPortfolio(@RequestBody PortfolioInfo pfi) {
				System.out.println("====================addNewPortfolio=====================");
				pir.addNewPortfolio(pfi.getUser_id(), pfi.getPortfolio_name(), pfi.getPortfolio_investment());
				// pir.save(pfi);
				System.out.println(pfi);
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				System.out.println("port id - " + pir.getPortfolioId(pfi.getPortfolio_name()));
				System.out.println("========================================================");
				
			}
		
		@PostMapping("/create/add/getStockListAndPercentAllocation")
			public void getAllStockListAndPercentageAllocation(@RequestBody StockList a) {
					System.out.println("====================getAllStockListAndPercentageAllocation=====================");
					System.out.println(a);
					System.out.println("port name - " + (String) a.getPrtID());
					System.out.println("percentALloc - " + (ArrayList<Integer>) a.getPercentAlloc());
					System.out.println("stock LIst - " + (ArrayList<String>) a.getStockList());
					
					String prtID = pir.getPortfolioId(a.getPrtID());
					ArrayList<String> stockList = a.getStockList();
					ArrayList<Integer> percentAlloc = a.getPercentAlloc();
					System.out.println(prtID);
					System.out.println(stockList);
					System.out.println(percentAlloc);
				
					for (int i = 0; i < stockList.size(); i++)
						awr.addAssetWeights(stockList.get(i), prtID, percentAlloc.get(i), 100);
						System.out.println("===============================================================================");
				}
		

			
		@PostMapping("/create/add/add2")
		public void asd(@RequestBody Hh h) {
			boolean valueAdd = false;
				StockList a = (StockList) h.getS();
				PortfolioInfo pfi = (PortfolioInfo) h.getP();
				System.out.println("====================addNewPortfolio=====================");
				pir.addNewPortfolio(pfi.getUser_id(), pfi.getPortfolio_name(), pfi.getPortfolio_investment());
				// pir.save(pfi);
				System.out.println(pfi);
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				System.out.println("port id - " + pir.getPortfolioId(pfi.getPortfolio_name()));
				System.out.println("========================================================");
				
				System.out.println("====================getAllStockListAndPercentageAllocation=====================");
				
				System.out.println(a);
				System.out.println("port name - " + (String) a.getPrtID());
				System.out.println("percentALloc - " + (ArrayList<Integer>) a.getPercentAlloc());
				System.out.println("stock LIst - " + (ArrayList<String>) a.getStockList());
				
				String prtID = pir.getPortfolioId(a.getPrtID());
				ArrayList<String> stockList = a.getStockList();
				ArrayList<Integer> percentAlloc = a.getPercentAlloc();
				System.out.println(prtID);
				System.out.println(stockList);
				System.out.println(percentAlloc);
				
				for (int i = 0; i < stockList.size(); i++) {
				awr.addAssetWeights(stockList.get(i), prtID, percentAlloc.get(i), 100);
				valueAdd = true;
			}
		PostClass pc = new PostClass();
		
			if (valueAdd) {
				Portfolio_Performance p = pc.getPerformanceMeasure(stockList, percentAlloc);
				p.setPortfolio_id(prtID);
				umr.save(p);
			}
		
		System.out.println("===============================================================================");
		
		System.out.println("--------------------------------Optimze Portfolio------------------------------");
		// pc.getOptimizePortfolio(stockList, percentAlloc);
		System.out.println("--------------------------------Optimze Portfolio------------------------------");
		
		// Portfolio_Performance p1 = new Portfolio_Performance();
		// System.out.println("--------------------------------getPredictionPortfolio------------------------------");
		// p1 = pc.getPredictionPortfolio(stockList, percentAlloc);
		// PortfolioPrediction pp = new PortfolioPrediction();
		// pp.setPortfolio_id(prtID);
		// pp.setPercentage_return(p1.getFive_year_return());
		// pp.setJensens_alpha(p1.getJensens_alpha());
		// pp.setPortfolio_beta(p1.getPortfolio_beta());
		// pp.setSharpe_ratio(p1.getSharpe_ratio());
		// pp.setValue_at_risk(p1.getValue_at_risk());
		// pp.setMonth("Jan 2021");
		// ppr.save(pp);
		// 
		// System.out.println("--------------------------------getPredictivonPortfolio------------------------------");
		}
		
	//Fetch portfolio names from list
		@GetMapping("/home/porfolioName/{id}")
			public List<String> getPortfolioNameList(@PathVariable int id) {
				System.out.println(pir.getPortfolioNameList(id));
				return (List<String>) pir.getPortfolioNameList(id);
			}
		
		
	//Fetch Stock Count and its sector
		@GetMapping("/home/sect/getSelectedSector/{sector}")
			public List<String> getSelectedSector(@PathVariable String sector) {
			System.out.println("====================getSectorNames=====================");
			
			System.out.println("portfolio name - "+sector);
			System.out.println("portfolio id = "+pir.getPortfolioId(sector));
			String portId = pir.getPortfolioId(sector);
			System.out.println(awr.getSectorNames(portId));
			
			System.out.println("=========================getSectorNames ends===============================");
			return (List<String>) awr.getSectorNames(portId) ;
		}
		
		
//Fetch sector and its stock selected
        @GetMapping("/home/stock/getStockList/{stockList}")
        public List<String> getStockList(@PathVariable String stockList) {
                        System.out.println("====================getStockNames======================");
                        String portId = pir.getPortfolioId(stockList);
                        return (List<String>) awr.getStockList(portId);
        }
		
//Sector data for pie chart 
		@GetMapping("/home/chart/sectorData/{id}")
			public List<SectorWiseStockCount> getSectorChartData(@PathVariable String id) {
				System.out.println(
				"============================getSectorChartData===================================================");
				
				String prtID = pir.getPortfolioId(id);
				System.out.println(prtID);
				System.out.println(sdr.getSectorList(prtID));
				List<List<Object>> a = sdr.getSectorList(prtID);
				List<SectorWiseStockCount> sd = new ArrayList<>();
				
				for(List<Object> l:a) {
					SectorWiseStockCount s = new SectorWiseStockCount((String)l.get(0),(BigInteger) l.get(1)); 
					System.out.println(l.get(0)+" "+l.get(1));
					sd.add(s);
					System.out.println("-------------sd---------------------");
					System.out.println(sd);
					System.out.println("----------------------------------");
				
				}
				System.out.println("===============================================================================");
				
				return sd;
			//return (List<SectorWiseStockCount>) sdr.getSectorList(prtID);
			}
		
		
		@GetMapping("/home/chart/assetWeights/{id}")
			public List<AssetWeights> getAssetWeightsData(@PathVariable String id) {
				System.out.println(
				"============================getAssetWeightsData===================================================");
				
				String prtID = pir.getPortfolioId(id);
				System.out.println(prtID);
				System.out.println(awr.getAssetWeight(prtID));
				System.out.println("===============================================================================");
				
				return (List<AssetWeights>) awr.getAssetWeight(prtID);
			}
		
		
		
		@GetMapping("/home/chart/chartData/{id}")
			public LineHelper getChartData(@PathVariable String id) {
				System.out.println("============================getChartData===================================================");
				String prtID=null;
				LineHelper lh = new LineHelper();
					if(id.equals("REC_1") || id.equals("REC_2")) {
						prtID = id;
						System.out.println(prtID);
					}
					else {
						prtID = pir.getPortfolioId(id);
						System.out.println(prtID);
					
					}
				
				lh.setMonteCarlo(cdr.getLineChartMonteCarlo(prtID));
				lh.setDl(cdr.getLineChartDl(prtID));
				lh.setDynamicDl(cdr.getLineChartDynamicDl(prtID));
				lh.setDt(cdr.getLineChartDate(prtID));
				lh.setNifty50(cdr.getNifty50(prtID));
				
				System.out.println("===============================================================================");
				
				return lh;
			}
		
		
		@GetMapping("/home/portfolioPerformance/{name}")
			public Portfolio_Performance getPortfolioPerformance(@PathVariable String name) {
				String prtId = pir.getPortfolioId(name);
				Portfolio_Performance pp = pprr.getPortfolioPerformance(prtId);
				return pp;
			}
		
		
		@GetMapping("/portfolioPredction/predValues/{id}")
			public List<PortfolioPrediction> getPortfolioPdferformance(@PathVariable String id) {
				String prtId = pir.getPortfolioId(id);
				return ppr.getPredictionValues(prtId);
			
			}
		
//    @Scheduled(fixedRate = 3600000)
		public void portfolioPrediction() {
			int user_id = 1;
			ArrayList<String> portfolioNameList = (ArrayList<String>) pir.getPortfolioNameList(user_id);
			System.out.println("portFolionameList" + portfolioNameList);
			
			for (String portName : portfolioNameList) {
				System.out.println("portName - " + portName);
				ArrayList<Double> stockAllocationList = new ArrayList<>();
				ArrayList<String> stockList = new ArrayList<>();
				
				String prtID = pir.getPortfolioId(portName);
				Double investmentCost = pir.getPortfolioInvestment(prtID);
				
				System.out.println("Investment cost of prt id (" + prtID + ") is - " + investmentCost);
				stockList.add("investmentCost");
				stockAllocationList.add(investmentCost);
				ppr.deletePortfolioPrediction(prtID);
				
				ArrayList<AssetWeights> assetWeightList = (ArrayList<AssetWeights>) awr.getAssetWeight(prtID);
				
				for (AssetWeights aw : assetWeightList) {
					stockList.add(aw.getAsset_id());
					stockAllocationList.add(aw.getWeight());
				}
				System.out.println(stockList);
				System.out.println(stockAllocationList);
				
				ArrayList<Portfolio_Performance> p2;
				PostClass pc = new PostClass();
				System.out.println("--------------------------------getPredictionPortfolio------------------------------");
				p2 = pc.getPredictionPortfolio(stockList, stockAllocationList);
				
				for (Portfolio_Performance p1 : p2) {
				
					PortfolioPrediction pp = new PortfolioPrediction();
					pp.setPortfolio_id(prtID);
					pp.setPercentage_return(p1.getFive_year_return());
					pp.setJensens_alpha(p1.getJensens_alpha());
					pp.setPortfolio_beta(p1.getPortfolio_beta());
					pp.setSharpe_ratio(p1.getSharpe_ratio());
					pp.setValue_at_risk(p1.getValue_at_risk());
					pp.setMonth(p1.getMonth());
					pp.setInvestment_Cost(p1.getInvestment_Cost());
					pp.setLatest_value(p1.getLatest_value());
					pp.setOverall_gain(p1.getOverall_gain());
					pp.setTodays_gain(p1.getTodays_gain());
					System.out.println("portfolio prediction - " + pp);
					ppr.save(pp);
					System.out.println("saved - prtID- " + prtID);
					
				}
				
				System.out.println("this is p1" + p2);
				
				System.out.println("--------------------------------getPredictionPortfolio------------------------------");
				
			}
		}
		
// @Scheduled(fixedRate = 3600000)
		public void optimizedPortfolio() {
			int user_id = 1;
			ArrayList<String> portfolioNameList = (ArrayList<String>) pir.getPortfolioNameList(user_id);
			// System.out.println("portFolionameList"+ portfolioNameList);
			
			for (String portName : portfolioNameList) {
				// System.out.println("portName - "+portName);
				ArrayList<Double> stockAllocationList = new ArrayList<>();
				ArrayList<String> stockList = new ArrayList<>();
				
				String prtID = pir.getPortfolioId(portName);
				
				System.out.println("prt id - (" + prtID + ") ");
				
				cdr.deleteChartData(prtID);
				
				ArrayList<AssetWeights> assetWeightList = (ArrayList<AssetWeights>) awr.getAssetWeight(prtID);
				for (AssetWeights aw : assetWeightList) {
					stockList.add(aw.getAsset_id());
					stockAllocationList.add(aw.getWeight());
				}
				
				// System.out.println(stockList);
				// System.out.println(stockAllocationList);
				
				ArrayList<ChartData> p2;
				PostClass pc = new PostClass();
				System.out.println("--------------------------------getPredictionPortfolio------------------------------");
				p2 = pc.getOptimizePortfolio(stockList, stockAllocationList);
				
				for (ChartData p1 : p2) {
				
					p1.setPortfolio_id(prtID);
					cdr.saveChartData(p1.getPortfolio_id(), p1.getDate(), p1.getMonte_carlo(), p1.getDl(),
					p1.getDynamic_dl(),p1.getNifty50());
					// System.out.println("saved - prtID- " + p1.getPortfolio_id()+" monteCarlo - "+p1.getMonte_carlo()
					// +" Dl-"+p1.getDl());
				
				}
				
				System.out.println("this is p1" + p2);
				
				System.out.println("--------------------------------getPredictionPortfolio------------------------------");
				
			}
		ArrayList<String> al = new ArrayList<>();
		al.add("REC_1");
		al.add("REC_2");
		for(String rPortId:al) {
		ArrayList<Double> stockAllocationList = new ArrayList<>();
		ArrayList<String> stockList = new ArrayList<>();
		cdr.deleteChartData(rPortId);
		
		ArrayList<RecommendedAssetWeights> assetWeightList = (ArrayList<RecommendedAssetWeights>) raw.getRAssetWeight(rPortId);
			for (RecommendedAssetWeights aw : assetWeightList) {
				stockList.add(aw.getAsset_id());
				stockAllocationList.add(aw.getWeight());
			}
			ArrayList<ChartData> p2;
			PostClass pc = new PostClass();
			System.out.println("--------------------------------getPredictionPortfolio------------------------------");
			p2 = pc.getOptimizePortfolio(stockList, stockAllocationList);
			
			for (ChartData p1 : p2) {
			System.out.println(p1);
			p1.setPortfolio_id(rPortId);
			cdr.saveChartData(p1.getPortfolio_id(), p1.getDate(), p1.getMonte_carlo(), p1.getDl(),
			p1.getDynamic_dl(),p1.getNifty50());
			
			}
		
		
		}
		
	}
		  
		  @SuppressWarnings("unchecked")
		//   @Scheduled(fixedRate = 36000000)
		                public void recommendedPortfolio1() {
		                                PostClass pc = new PostClass();
		                                ArrayList<RecommendedPortPerformance> p1 = new ArrayList<>();
		                                ArrayList<RecommendedAssetWeights> p2 = new ArrayList<>();
		                                ArrayList<Object> p = new ArrayList<>();

		                                p = pc.recommendedPortfolio();
		                                p1 = (ArrayList<RecommendedPortPerformance>) p.get(0);
		                                p2 = (ArrayList<RecommendedAssetWeights>) p.get(1);

		                                System.out.println("FINAL RESULT - " + p1);
		                                p1.get(0).setPortfolio_id("REC_1");
		                                p1.get(1).setPortfolio_id("REC_2");
		                                RecommendedPortPerformance rp1 = p1.get(0);
		                                RecommendedPortPerformance rp2 = p1.get(1);
		                                rpp.deleteRecommendedPortfolio("REC_1");
		                                rpp.deleteRecommendedPortfolio("REC_2");

		                                rpp.saveRecommendedPortfolio(rp1.getPortfolio_id(), rp1.getFive_year_return(), rp1.getSharpe_ratio(),
		                                                                rp1.getValue_at_risk(), rp1.getPortfolio_beta(), rp1.getTreynor_ratio(), rp1.getJensens_alpha());

		                                rpp.save(rp2);
		                                raw.deletePortfolioAssetWeight("REC_1");
		                                raw.deletePortfolioAssetWeight("REC_2");
		                                System.out.println("recommended asset weight - " + p2);

		                                for (RecommendedAssetWeights rAW : p2) {
		                                                System.out.println(rAW.getPortfolio_id());
		                                                raw.addRecommendedAssetWeights(rAW.getAsset_id(), rAW.getPortfolio_id(), rAW.getWeight(), rAW.getAmount());

		                                }

		                                raw.removeNull("REC_1");
		                                raw.removeNull("REC_2");

		                }
		  
			@GetMapping("/home/recommendedPorfolioName/{id}")
				public List<String> getRecommendedPortfolioNameList(@PathVariable int id) {
					System.out.println(rpp.getrecommendedPortfolioNameList());
					return (List<String>) rpp.getrecommendedPortfolioNameList();
			}
		 
			
    
            
//Recommended sector data display in the list
			
            @GetMapping("/Recom/getRStockCount/{portfolio_id}")
	            public List<String> getRStockCount(@PathVariable String portfolio_id) {
	                            System.out.println("====================getSectorNames=====================");
	                            System.out.println(raw.getRSectorWiseCount(portfolio_id));
	                            System.out.println("=========================getSectorNames ends===============================");
	                            return (List<String>) raw.getRSectorWiseCount(portfolio_id) ;
	            }
//Stock and sector data in list
            @GetMapping("/recommend/stock/getStockList/{sectorStock}")
	            public List<String> getRecStockList(@PathVariable String sectorStock) {
	                            System.out.println("====================getStockNames======================");
	                            return (List<String>) raw.getRecStockList(sectorStock);
	            }

            
//Recommend Pie Chart 
            @GetMapping("/chart/RSectorData/{portfolio_id}")
	            public List<RecSectorWiseStockCount> getRSectorChartData(@PathVariable String portfolio_id) {
	                            System.out.println(
	                                            "============================getSectorChartData===================================================");
	
	                            System.out.println(raw.getSectorList(portfolio_id));
	                            List<List<Object>> a = raw.getSectorList(portfolio_id);
	                            List<RecSectorWiseStockCount> rsd = new ArrayList<>();
	                            
	                            for(List<Object> l:a) {
	                                            RecSectorWiseStockCount s = new RecSectorWiseStockCount((String)l.get(0),(BigInteger) l.get(1)); 
	                                            System.out.println(l.get(0)+" "+l.get(1));
	                                            rsd.add(s);
	                                            System.out.println("-------------sd---------------------");
	                                            System.out.println(rsd);
	                                            System.out.println("----------------------------------");
	
	                            }
	                                            
	            System.out.println("===============================================================================");
	
	                            return rsd;
	                            //return (List<SectorWiseStockCount>) sdr.getSectorList(prtID);
	            }
//Recommend page functions end here
		
			@GetMapping("/home/recommendedPortfolioPerformance/{name}")
			public RecommendedPortPerformance getRecommendedPortfolioPerformance(@PathVariable String name) {
				RecommendedPortPerformance pp = rpp.getRecommendedPortfolioPerformance(name);
				return pp;
			}
			
			
			@RequestMapping(value = "/home/chart/recommendedAssetWeights/{id}", method = RequestMethod.GET)
			public List<RecommendedAssetWeights> test(@PathVariable String id) {
				System.out.println(
				"============================getAssetWeightsData===================================================");
				System.out.println(id);
				System.out.println(raw.getRecommendedAssetWeight(id));
				System.out.println("===============================================================================");
				
				return (List<RecommendedAssetWeights>) raw.getRecommendedAssetWeight(id);
			}

  }

