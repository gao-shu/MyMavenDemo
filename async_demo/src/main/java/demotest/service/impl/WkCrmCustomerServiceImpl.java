package demotest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demotest.dao.WkCrmCustomerDao;
import demotest.entity.WkCrmCustomer;
import demotest.service.WkCrmCustomerService;
import org.springframework.stereotype.Service;

/**
 * 客户表(WkCrmCustomer)表服务实现类
 *
 * @author makejava
 * @since 2022-04-08 14:38:05
 */
@Service("wkCrmCustomerService")
public class WkCrmCustomerServiceImpl extends ServiceImpl<WkCrmCustomerDao, WkCrmCustomer> implements WkCrmCustomerService {

}

