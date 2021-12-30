package com.gaoshu.springbootmybatisplus.PO;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author gaoshu
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户表")
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("age")
//    @FieldBind(type = "user_sex", target = "sexText")
    @TableField(update = "%s + 1", updateStrategy=FieldStrategy.IGNORED)
    private Integer age;

    @ApiModelProperty("email")
    private String email;

    @TableLogic
    @TableField(fill = FieldFill.INSERT, value = "deleted")
    private Integer deleted;

    @ApiModelProperty("sex")
    @TableField(fill = FieldFill.UPDATE, value = "sex")
    private String sex;

//    @ApiModelProperty("sex")
//    @TableField(exist = false)
//    private com.gaoshu.common.SexEnum sexEnum;

    @ApiModelProperty("mobile")
//    @FieldSensitive(type = "userMobile")
    private String mobile;

    @ApiModelProperty(value = "0男，1女")
    private Boolean sexBit;

}
