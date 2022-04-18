package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.PortfolioInfo;

@Repository
@Transactional
public interface PortfolioInfoRepo  extends CrudRepository<PortfolioInfo, Integer>{
	
	@Query(value = "SELECT portfolio_name FROM portfolio_info WHERE user_id=?1", nativeQuery = true)
    public List<String> getPortfolioNameList(int id);
	
	@Query(value = "SELECT portfolio_id FROM portfolio_info WHERE portfolio_name=?1 limit 1", nativeQuery = true)
    public String getPortfolioId(String name);
	
	@Modifying
	@Query(value = "INSERT INTO portfolio_info VALUES(LEFT(MD5(RAND()), 4), ?1,?2,?3)", nativeQuery = true)
    public void addNewPortfolio(int userId, String portfolioName, double portfolioInvestment);
	
	@Query(value = "SELECT portfolio_investment FROM portfolio_info WHERE portfolio_id=?1", nativeQuery = true)
    public double getPortfolioInvestment(String prtfID);
	
	


}
