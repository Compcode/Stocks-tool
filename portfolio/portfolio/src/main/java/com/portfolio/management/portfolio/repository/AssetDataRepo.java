package com.portfolio.management.portfolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.AssetData;

@Repository
@Transactional
public interface AssetDataRepo extends CrudRepository<AssetData, Integer> {
	@Query(value = "SELECT closing_price FROM asset_data a inner join asset_weights b on "
			+ "a.asset_id = b.asset_id WHERE b.portfolio_id= ?1", nativeQuery = true)
	public List<String> getAllstockClosingPrice(String stkLTP);

}
