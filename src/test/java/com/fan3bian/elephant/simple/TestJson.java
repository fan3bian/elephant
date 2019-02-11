package com.fan3bian.elephant.simple;

import com.fan3bian.elephant.domain.pojo.Buckets;
import com.fan3bian.elephant.domain.pojo.JsonRootBean;
import com.fan3bian.elephant.domain.pojo._source;
import com.fan3bian.elephant.utils.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class TestJson {
    public static void main(String[] args) throws Exception{
        String filePath = "d:\\orderA.json";

        String msgBody = FileUtils.readFileToString(new File(filePath),"UTF-8");
        JsonRootBean jsonRootBean = JsonUtil.fromJson(msgBody,JsonRootBean.class);
        List<Buckets> buckets = jsonRootBean.getAggregations().getDept_no().getBuckets();
        for (Buckets bucket : buckets) {
            List<Buckets> innerbuckets = bucket.getSp_name().getBuckets();
            for (Buckets innerbucket : innerbuckets) {
                int num = (int)innerbucket.getTop().getHits().getTotal();
                _source source = innerbucket.getTop().getHits().getHits().get(0).get_source();
                System.out.println(source.getDept_name()+"  "+source.getDept_no() +"    " +source.getIsv_source()+"     "+source.getSp_name()+"  "+num);
            }
        }
        System.out.println();
    }
}
