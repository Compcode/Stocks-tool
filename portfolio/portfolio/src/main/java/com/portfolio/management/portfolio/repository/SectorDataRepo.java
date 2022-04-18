package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.SectorData;

@Repository
@Transactional
public interface SectorDataRepo extends CrudRepository<SectorData, Integer> {
	
	@Query(value = "SELECT DISTINCT industry FROM sector_data", nativeQuery = true)
	public List<String> getSectorData(String industry);  
	
	
	@Query(value = "SELECT asset_id FROM sector_data WHERE industry= ?1", nativeQuery = true)
	public List<String> getSectorInfo(String industry); 
	
	
	@Query(value = "SELECT Distinct asset_id FROM sector_data", nativeQuery = true)
	public List<String> getAllSectorInfo(String industry);
	
	@Query(value = "select b.industry as stockName,count(b.industry) as sector_wise_count from ((\r\n"
			+ "SELECT * FROM portfolio_management.asset_weights WHERE portfolio_id = ?1) a left join \r\n"
			+ "portfolio_management.sector_data b  on a.asset_id = b.asset_id) group by b.industry;",nativeQuery = true) 
	 public List<List<Object>> getSectorList(String id);
}
