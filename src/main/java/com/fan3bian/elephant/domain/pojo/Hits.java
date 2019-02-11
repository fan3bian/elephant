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
public class Hits {

    private List<Hit> hits;
    private double max_score;
    private long total;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public void setMax_score(double max_score) {
         this.max_score = max_score;
     }
     public double getMax_score() {
         return max_score;
     }

    public void setTotal(long total) {
         this.total = total;
     }
     public long getTotal() {
         return total;
     }

}