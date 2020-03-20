package com.fan3bian.elephant.manger;

import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.dao.ReportMasterDao;
import com.fan3bian.elephant.domain.condition.ReportMasterCondition;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WarehouseTest extends ElephantApplicationTests {

    @Resource
    private ReportMasterDao reportMasterDao;

    @Test
    public void countStockGroupByWarehouseType() {
        ReportMasterCondition condition = new ReportMasterCondition();
        condition.setTenantId("0");
        List<Map<String, Object>> warehouseStock = reportMasterDao.countStockGroupByWarehouseType(condition);
        warehouseStock.forEach(map -> map.put("typeName", map.get("type") + "zhang"));
        System.out.println(warehouseStock);
    }

    @Test
    public void countStockGroupByProvince() {
        List<Map<String, Object>> warehouseStock = reportMasterDao.countStockGroupByProvince();
        System.out.println(warehouseStock);
    }

    @Test
    public void countWarehouse() {
        int count = reportMasterDao.countWarehouse();
        System.out.println(count);
    }

    @Test
    public void countWarehouseDetailByCond() {
        ReportMasterCondition condition = new ReportMasterCondition();
        condition.setProvinceId("110000");
        List<Map<String, Object>> maps = reportMasterDao.countWarehouseDetailByCond(condition);
//        maps.removeIf(map -> map.get("warehouseId") == null);
        System.out.println(maps);
        List<String> warehouseIds = maps.stream().map(map -> map.get("warehouseId").toString()).collect(Collectors.toList());
        System.out.println(warehouseIds);
        ReportMasterCondition cond = new ReportMasterCondition();
        cond.setWarehouseIdList(warehouseIds);
        List<Map<String, Object>> goodsTag = reportMasterDao.getGoodsTagByWarehouse(cond);
        System.out.println(goodsTag);
        final Map<String, Object> collect = goodsTag.stream().collect(Collectors.toMap(x -> x.get("warehouseId").toString(), x -> x.get("tags")));
        System.out.println(collect);
        maps.forEach(map -> {
                map.put("tags", collect.get(map.get("warehouseId")));
        });
        System.out.println(maps);
    }

    @Test
    public void countManufacturerDetailByCond() {
        ReportMasterCondition condition = new ReportMasterCondition();
        condition.setProvinceId("232323");
        List<Map<String, Object>> maps = reportMasterDao.countManufacturerDetailByCond(condition);
//        System.out.println(maps);
//        maps.removeIf(map -> map.get("manufacturerNo") == null);
        System.out.println(maps);
        List<String> manufacturerNoList = maps.stream().map(map -> (String) map.get("manufacturerNo")).collect(Collectors.toList());
        System.out.println(manufacturerNoList);
        ReportMasterCondition cond = new ReportMasterCondition();
        cond.setManufacturerNoList(manufacturerNoList);
        List<Map<String, Object>> goodsTag = reportMasterDao.getGoodsTagByManufacturer(cond);
        System.out.println(goodsTag);
        final Map<String, Object> collect = goodsTag.stream().collect(Collectors.toMap(x -> (String)x.get("manufacturerNo"), x -> x.get("tags")));
        System.out.println(collect);
        maps.forEach(map -> {
            String  manufacturerNo = (String)map.get("manufacturerNo");
            if(collect.containsKey(manufacturerNo)){
                map.put("tags", collect.get(manufacturerNo));
            }
        });
        System.out.println(maps);
    }

}
