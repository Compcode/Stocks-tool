package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.AssetInfo;
import com.portfolio.management.portfolio.model.AssetWeights;

@Repository
@Transactional
public interface AssetInfoRepo extends CrudRepository<AssetInfo, Integer> {

	@Query(value = "SELECT asset_name FROM asset_info WHERE asset_type=?1", nativeQuery = true)
	public List<String> getStockList(String assetType);

}
