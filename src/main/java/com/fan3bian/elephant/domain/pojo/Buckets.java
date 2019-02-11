/**
  * Copyright 2019 bejson.com 
  */
package com.fan3bian.elephant.domain.pojo;

/**
 * Auto-generated: 2019-02-11 17:1:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Buckets {

    private long doc_count;
    private String key;
    private Sp_name sp_name;
    private Top top;
    public void setDoc_count(long doc_count) {
         this.doc_count = doc_count;
     }
     public long getDoc_count() {
         return doc_count;
     }

    public void setKey(String key) {
         this.key = key;
     }
     public String getKey() {
         return key;
     }

    public void setSp_name(Sp_name sp_name) {
         this.sp_name = sp_name;
     }
     public Sp_name getSp_name() {
         return sp_name;
     }

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }
}