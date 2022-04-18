package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.Portfolio_Performance;

@Repository
@Transactional
public interface PortfolioPerformanceRepo extends CrudRepository<Portfolio_Performance, Integer> {
	
	@Override
	public List<Portfolio_Performance> findAll();
	
	@Query(value = "SELECT * FROM portfolio_performance WHERE portfolio_id=?1 LIMIT 1", nativeQuery = true)
	public Portfolio_Performance getPortfolioPerformance(String prtID);
	
		
}
