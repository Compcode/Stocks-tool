package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.PortfolioPrediction;

@Repository
@Transactional
public interface PortfolioPredictionRepo extends CrudRepository<PortfolioPrediction, Integer> {
	
	@Query(value = "SELECT * FROM portfolio_prediction WHERE portfolio_id=?1", nativeQuery = true)
     public List<PortfolioPrediction> getPredictionValues(String id);
	
	@Modifying
	@Query(value = "DELETE FROM portfolio_prediction WHERE portfolio_id=?1", nativeQuery = true)
    public void deletePortfolioPrediction(String id);
	
	
}
