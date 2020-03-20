package com.fan3bian.elephant.domain.condition;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ReportMasterCondition implements Serializable {

    private String provinceId;

    private List<String> warehouseIdList;

    private List<String> manufacturerNoList;

    private String tenantId;
}
