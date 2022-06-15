package com.myproject.manager.vo;

import lombok.Data;

/**
 * FileName:    QueryParam

 * Description:
 */
@Data
public class QueryParam {
    private String id;
    //用户名
    private String itemname;

    // 昵称
    private String typename;

    // 号码
    private String longitude;

    // 性别
    private String finalOption;

    // 创建时间
    private String time;

    @Override
    public String toString() {
        return "QueryParam{" +
                "id="+id+'\''+
                "itemname='" + itemname + '\'' +
                ", typename='" + typename + '\'' +
                ", longitude='" + longitude + '\'' +
                ", finalOption='" + finalOption + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}