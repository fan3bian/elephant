/**
  * Copyright 2019 bejson.com 
  */
package com.fan3bian.elephant.domain.pojo;
import java.util.List;

/**
 * Auto-generated: 2019-02-11 17:1:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Dept_no {

    private List<Buckets> buckets;
    private int doc_count_error_upper_bound;
    private int sum_other_doc_count;
    public void setBuckets(List<Buckets> buckets) {
         this.buckets = buckets;
     }
     public List<Buckets> getBuckets() {
         return buckets;
     }

    public void setDoc_count_error_upper_bound(int doc_count_error_upper_bound) {
         this.doc_count_error_upper_bound = doc_count_error_upper_bound;
     }
     public int getDoc_count_error_upper_bound() {
         return doc_count_error_upper_bound;
     }

    public void setSum_other_doc_count(int sum_other_doc_count) {
         this.sum_other_doc_count = sum_other_doc_count;
     }
     public int getSum_other_doc_count() {
         return sum_other_doc_count;
     }

}