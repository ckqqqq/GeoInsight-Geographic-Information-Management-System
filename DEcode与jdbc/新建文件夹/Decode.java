package com.xiaoyuan.usermanager.service.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import com.xiaoyuan.usermanager.db.entity.User;

import com.xiaoyuan.usermanager.utils.JDBC;
/**
 * 解码
 * 一共是36位，分为震情编码（26位）来源码（3位）载体码（1位）灾情码（6位）
 * 震情编码（26位）细分为地理信息码（12位）时间码（14位）（年4月2日2时2分2秒2）
 * 来源码三位，具体信息见项目需求
 * 载体编码一位，具体信息见项目需求
 * 灾情编码分为灾害编码（3位）灾情指标代码（3位）
*/
public class Decode {

    JDBC jdbc = null;
    String id;
    String detail;
    String address;
    String date;
    String source;
    String carrier;
    String classification;
    String indicator;

    public String address_decode(String address_code) throws SQLException {
        String sql="SELECT * FROM address";
        ResultSet rs=jdbc.query(sql);
        try {
            while(rs.next()) {
                String province_id=rs.getString(1);
                String province_name=rs.getString(2);
                if(province_id.equals(address_code)){
                    //System.out.println("find it,address code is"+province_name);
                    return province_name;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "address code not exist!";
    }

    public String date_decode(String date_code){
        String year = date_code.substring(0,4);
        String month = date_code.substring(4,6);
        String day = date_code.substring(6,8);
        String hour = date_code.substring(8,10);
        String minute = date_code.substring(10,12);
        String second = date_code.substring(12,14);
        return year+" "+month+" "+day+" "+hour+":"+minute+":"+second;
    }

    public String source_decode(String source_code) throws SQLException {
        String sql="SELECT *FROM source";
        ResultSet rs=jdbc.query(sql);
        try {
            while(rs.next()) {
                String source_id=rs.getString(1);
                String detail_info=rs.getString(2);
                if(source_id.equals(source_code)){
                    //System.out.println("find it,information source is "+detail_info);
                    return detail_info;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "source code not exist!";
    }

    public String carrier_decode(String carrier_code) throws SQLException {
        String sql="SELECT *FROM carrier";
        ResultSet rs=jdbc.query(sql);
        try {
            while(rs.next()) {
                String carrier_id=rs.getString(1);
                String carrier_info=rs.getString(2);
                if(carrier_id.equals(carrier_code)){
                    //System.out.println("find it,carrier  is "+carrier_info);
                    return carrier_info;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "carrier code not exist!";
    }

    public String classification_decode(String disaster_classification_code) throws SQLException {
        String sql="SELECT *FROM disaster_classification";
        ResultSet rs=jdbc.query(sql);
        try {
            while(rs.next()) {
                String disaster_id=rs.getString(1);
                String disaster_name=rs.getString(2);
                if(disaster_id.equals(disaster_classification_code)){
                    //System.out.println("find it,carrier  is "+disaster_name);
                    return disaster_name;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "disaster classification code not exist!";
    }

    public String indicator_decode(String disaster_indicator_code) throws SQLException {
        String sql="SELECT *FROM disaster_indicator";
        ResultSet rs=jdbc.query(sql);
        try {
            while(rs.next()) {
                String indicator_id=rs.getString(1);
                String indicator=rs.getString(2);
                if(indicator_id.equals(disaster_indicator_code)){
                    //System.out.println("find it,carrier  is "+indicator);
                    return indicator;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "disaster indicator code not exist!";
    }

    /**
     * 总体的解码函数，各个分函数也可以在上面根据函数名字来调用
    * */
    public String Decode_All(String id) throws SQLException, ClassNotFoundException {
        jdbc = new JDBC();

        this.id = id;
        address = address_decode(id.substring(0,12));
        date = date_decode(id.substring(12,26));
        source = source_decode(id.substring(26,29));
        carrier = carrier_decode(id.substring(29,30));
        classification = classification_decode(id.substring(30,33));
        indicator = indicator_decode(id.substring(33,36));
        return (address+" "+date+" "+source+" "+carrier+" "+classification+" "+indicator);
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getClassification() {
        return classification;
    }

    public String getIndicator() {
        return indicator;
    }
}
