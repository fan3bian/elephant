package com.fan3bian.elephant.simple;

import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.domain.pojo.Buckets;
import com.fan3bian.elephant.domain.pojo.JsonRootBean;
import com.fan3bian.elephant.domain.pojo._source;
import com.fan3bian.elephant.utils.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Lists;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJson {
    public static void main(String[] args) throws Exception{

        User user = new User();
        user.setId(1L);
        user.setPassword("2");
        ArrayList<User> users = Lists.newArrayList(user);
        users.add(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(users);
        System.out.println(s);
        byte[] value = objectMapper.writeValueAsBytes(user);
        Object o = objectMapper.readValue(value, Object.class);
        System.out.println(o.getClass());
//        objectMapper
//        String filePath = "d:\\orderA.json";
//
//        String msgBody = FileUtils.readFileToString(new File(filePath),"UTF-8");
//        JsonRootBean jsonRootBean = JsonUtil.fromJson(msgBody,JsonRootBean.class);
//        List<Buckets> buckets = jsonRootBean.getAggregations().getDept_no().getBuckets();
//        for (Buckets bucket : buckets) {
//            List<Buckets> innerbuckets = bucket.getSp_name().getBuckets();
//            for (Buckets innerbucket : innerbuckets) {
//                int num = (int)innerbucket.getTop().getHits().getTaxInAmount();
//                _source source = innerbucket.getTop().getHits().getHits().get(0).get_source();
//                System.out.println(source.getDept_name()+"  "+source.getDept_no() +"    " +source.getIsv_source()+"     "+source.getSp_name()+"  "+num);
//            }
//        }
//        System.out.println();

    }

}
