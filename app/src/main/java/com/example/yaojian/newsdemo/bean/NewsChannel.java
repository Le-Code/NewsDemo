package com.example.yaojian.newsdemo.bean;

/**
 * Created by yaojian on 2018/1/29.
 */

public class NewsChannel {
    /**
     * 新闻频道名称
     */
    private String nc_name;

    /**
     * 新闻频道类型
     */
    private String nc_type;

    /**
     * 新闻频道的id
     */
    private String nc_id;

    public NewsChannel(){}

    public NewsChannel(String nc_name,String nc_type,String nc_id){
        this.nc_name = nc_name;
        this.nc_type = nc_type;
        this.nc_id = nc_id;
    }

    public String getNc_name() {
        return nc_name;
    }

    public void setNc_name(String nc_name) {
        this.nc_name = nc_name;
    }

    public String getNc_type() {
        return nc_type;
    }

    public void setNc_type(String nc_type) {
        this.nc_type = nc_type;
    }

    public String getNc_id() {
        return nc_id;
    }

    public void setNc_id(String nc_id) {
        this.nc_id = nc_id;
    }
}
