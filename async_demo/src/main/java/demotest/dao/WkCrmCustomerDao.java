package demotest.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import demotest.entity.WkCrmCustomer;

/**
 * 客户表(WkCrmCustomer)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-08 14:38:05
 */
public interface WkCrmCustomerDao extends BaseMapper<WkCrmCustomer> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WkCrmCustomer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WkCrmCustomer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WkCrmCustomer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WkCrmCustomer> entities);

}

