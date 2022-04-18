package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.Portfolio_Performance;
import com.portfolio.management.portfolio.model.RecommendedPortPerformance;

@Transactional
@Repository
public interface RecommendedPortPerformanceRepo extends CrudRepository<RecommendedPortPerformance, Integer> {
	
	@Modifying
	@Query(value = "Insert into recommended_port_performance values(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	public void saveRecommendedPortfolio(String prtID,Double fReturn,Double sharp_ratio,Double var,
			Double portfolio_beta,Double treynor_ratio,Double jenson_alpha);
	
	@Query(value = "SELECT portfolio_id FROM recommended_port_performance", nativeQuery = true)
    public List<String> getrecommendedPortfolioNameList();
	
	
	@Modifying
	@Query(value = "DELETE FROM recommended_port_performance WHERE portfolio_id=?1", nativeQuery = true)
    public void deleteRecommendedPortfolio(String id);
	
	@Query(value = "SELECT * FROM recommended_port_performance WHERE portfolio_id=?1", nativeQuery = true)
	public RecommendedPortPerformance getRecommendedPortfolioPerformance(String prtID);


}
