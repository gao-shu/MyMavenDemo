package com.gaoshu.entity.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gaoshu
 * @since 2021-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbBlogTagRelation对象", description="")
public class TbBlogTagRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "关系表id")
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Long relationId;

    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @ApiModelProperty(value = "标签id")
    private Integer tagId;

    @ApiModelProperty(value = "添加时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
