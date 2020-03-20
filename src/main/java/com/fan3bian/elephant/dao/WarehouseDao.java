package com.fan3bian.elephant.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WarehouseDao {

    List<Map<String, Object>> getWarehouseStock();
}
