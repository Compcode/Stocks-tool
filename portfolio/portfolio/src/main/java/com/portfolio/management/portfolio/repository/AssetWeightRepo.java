package com.portfolio.management.portfolio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.AssetWeights;

import helper.SectorWiseStockCount;

@Repository
@Transactional
public interface AssetWeightRepo extends CrudRepository<AssetWeights, Integer> {
	
	@Query(value = "SELECT * FROM asset_weights WHERE portfolio_id=?1", nativeQuery = true)
	public List<AssetWeights> getAssetWeight(String id);
	
	
	
	@Query(value = "SELECT sector_data.asset_id, sector_data.industry FROM portfolio_info JOIN asset_weights "
			+ " ON portfolio_info.portfolio_id = asset_weights.portfolio_id JOIN sector_data "
			+ " ON sector_data.asset_id = asset_weights.asset_id WHERE portfolio_info.portfolio_id= ?1", nativeQuery = true)
	public List<String> getStockList(String portfolioId);
	
	
	@Query(value = "select b.industry as stockName,count(b.industry) as sector_wise_count from ((\r\n"
			+ "SELECT * FROM portfolio_management.asset_weights WHERE portfolio_id = ?1) a left join \r\n"
			+ "portfolio_management.sector_data b  on a.asset_id = b.asset_id) group by b.industry;",nativeQuery = true)
	 public List<String> getSectorNames(String portName); 
	  
	
	@Modifying
	@Query(value = "INSERT INTO asset_weights VALUES(?1,?2,?3,?4)", nativeQuery = true)
	public void addAssetWeights(String assetId, String portfolioId, int weight, int amt);
	
	
}