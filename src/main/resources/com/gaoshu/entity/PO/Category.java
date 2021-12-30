package com.gaoshu.entity.PO;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gaoshu_category")
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;


}
