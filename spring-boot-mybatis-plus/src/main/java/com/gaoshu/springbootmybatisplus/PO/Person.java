package com.gaoshu.springbootmybatisplus.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author gaoshu
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Person对象", description="")
public class Person implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    @ApiModelProperty(value = "男女")
    private String sex;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "0男，1女")
    private Boolean sexBit;


}
