package com.portfolio.management.portfolio.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.ChartData;

@Repository
@Transactional
public interface ChartDataRepo  extends CrudRepository<ChartData, Integer>{
	
	@Query(value = "select * from chart_data where portfolio_id=?1", nativeQuery = true)
    public List<ChartData> getLineChartData(String id);
	
	@Query(value = "select date from chart_data where portfolio_id=?1", nativeQuery = true)
    public List<Date> getLineChartDate(String id);
	
	@Query(value = "select monte_carlo from chart_data where portfolio_id=?1", nativeQuery = true)
    public List<Double> getLineChartMonteCarlo(String id);
	
	@Query(value = "select dl from chart_data where portfolio_id=?1", nativeQuery = true)
    public List<Double> getLineChartDl(String id);
	
	@Query(value = "select dynamic_dl from chart_data where portfolio_id=?1", nativeQuery = true)
    public List<Double> getLineChartDynamicDl(String id);
	
	@Query(value = "select nifty50 from chart_data where portfolio_id=?1", nativeQuery = true)
    public List<Double> getNifty50(String id);

	@Modifying
	@Query(value = "DELETE FROM chart_data WHERE portfolio_id=?1", nativeQuery = true)
    public void deleteChartData(String id);
	
	@Modifying
	@Query(value = "INSERT INTO chart_data VALUES(?1,?2,?3,?4,?5 ,?6)", nativeQuery = true)
    public void saveChartData(String id, Date d, Double monteCarlo,Double Dl,Double dynamicDL,Double nifty50);
	

}
