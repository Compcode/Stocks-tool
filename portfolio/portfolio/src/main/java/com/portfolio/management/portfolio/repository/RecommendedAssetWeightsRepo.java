package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.AssetWeights;
import com.portfolio.management.portfolio.model.RecommendedAssetWeights;

@Repository
@Transactional
public interface RecommendedAssetWeightsRepo extends CrudRepository<RecommendedAssetWeights, Integer> {
	@Query(value = "SELECT * FROM recommended_asset_weights WHERE portfolio_id=?1", nativeQuery = true)
	public List<RecommendedAssetWeights> getRecommendedAssetWeight(String id);
	
	
	@Modifying
	@Query(value = "INSERT INTO recommended_asset_weights VALUES(?1,?2,?3,?4)", nativeQuery = true)
	public void addRecommendedAssetWeights(String assetId, String portfolioId, Double weight, Double amt);
	
	@Modifying
	@Query(value = "DELETE FROM recommended_asset_weights WHERE portfolio_id=?1", nativeQuery = true)
	public void deletePortfolioAssetWeight(String prtId);
	
	
	@Query(value = "SELECT * FROM recommended_asset_weights WHERE portfolio_id=?1", nativeQuery = true)
	public List<RecommendedAssetWeights> getRAssetWeight(String portId);

//Sector data with count
	@Query(value = "select industry as stockName, count(industry) from portfolio_management.recommended_asset_weights \r\n"
			+ "where portfolio_id= ?1 group by industry", nativeQuery = true)
	public List<List<Object>> getSectorList(String portfolio_id);
	
//Stock along with sector data
	@Query(value = "select a.asset_id, b.industry from portfolio_management.recommended_asset_weights a "
			+ "	left join portfolio_management.sector_data b on a.asset_id = b.asset_id where a.portfolio_id= ?1", nativeQuery = true)
	public List<String> getRecStockList(String sector);
//	select a.asset_id, a.portfolio_id, b.industry from portfolio_management.recommended_asset_weights a 
//	left join portfolio_management.sector_data b on a.asset_id = b.asset_id;
	
	@Query(value = "select industry, count(asset_id) from portfolio_management.recommended_asset_weights "
			+ "where portfolio_id = ?1 group by industry", nativeQuery = true)
	public List<String> getRSectorWiseCount(String portfolio_id);

	
	@Modifying
	@Query(value = "DELETE FROM recommended_asset_weights WHERE weight is null", nativeQuery = true)
	public void removeNull(String pID);

}


 