package com.zpy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("comment")//对应数据库的comment表
public class Comment {
    private Integer id;
    private Integer scenicId;
    private Integer star;
    private String customer;//品论人
    private String cimage;//评论人姓名
    private String comment;//评论

    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commentTime;//评论时间

}
