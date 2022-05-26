package com.xiaoyuan.usermanager.db.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * FileName:    User
 * Author:      小袁教程
 * Date:        2022/5/12 23:18
 * Description:
 */
@Data
public class User {

    // 主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 名称
    private String username;

    //
    private String typename;

    // 纬度varchat32
    private String latitude;//纬度

    // 经度 varchat32
    private String longitude;

    // 一个选项
    private Character finalOption;

    // 描述，文本形式
    @TableField(value = "`describe`")
    private String describe;

    // 创建时间
    @TableField(fill = FieldFill.INSERT) // insert操作时自动注入时间
    private Date gmtCreate;
}
