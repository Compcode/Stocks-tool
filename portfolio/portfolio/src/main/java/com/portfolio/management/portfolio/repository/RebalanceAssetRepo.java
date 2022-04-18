package com.portfolio.management.portfolio.repository;

import javax.transaction.Transactional;
import java.util.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.RebalanceAsset;


@Repository
@Transactional
public interface RebalanceAssetRepo extends CrudRepository<RebalanceAsset, Integer> {
	
	@Query(value ="SELECT asset_id FROM rebalance_asset WHERE portfolio_id=?1", nativeQuery = true)
	public List<String> getRebStockNames(String assetID);
	
	@Query(value ="SELECT weight FROM rebalance_asset WHERE portfolio_id=?1", nativeQuery = true)
	public List<String> getRebStockPercent(String percentage);
	
	@Query(value ="SELECT percent FROM rebalance_asset WHERE portfolio_id=?1", nativeQuery = true)
	public List<String> getRebAllocAmount(String allocation);
	
	
	
}
