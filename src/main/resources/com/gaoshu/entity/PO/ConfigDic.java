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
@TableName("gaoshu_config_dic")
@ApiModel(value="ConfigDic对象", description="")
public class ConfigDic implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "分类")
    private String type;

    @ApiModelProperty(value = "内容")
    private String value;

    @ApiModelProperty(value = "0,弃用 1，启用")
    private Boolean status;


}
