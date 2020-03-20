package com.fan3bian.elephant.dao;


import com.fan3bian.elephant.domain.condition.ReportMasterCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMasterDao {

    List<Map<String, Object>> countStockGroupByWarehouseType(ReportMasterCondition condition);

    List<Map<String, Object>> countStockGroupByProvince();

    int countWarehouse();

    List<Map<String, Object>> countWarehouseGroupByProvince();

    List<Map<String, Object>> countManufacturerGroupByProvince();

    List<Map<String, Object>> countWarehouseDetailByCond(ReportMasterCondition condition);

    List<Map<String, Object>> getGoodsTagByWarehouse(ReportMasterCondition condition);

    List<Map<String, Object>> countManufacturerDetailByCond(ReportMasterCondition condition);

    List<Map<String, Object>> getGoodsTagByManufacturer(ReportMasterCondition condition);


}
