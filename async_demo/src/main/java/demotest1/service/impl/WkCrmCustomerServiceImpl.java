package demotest1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demotest1.dao.WkCrmCustomerDao;
import demotest1.entity.WkCrmCustomer;
import demotest1.service.WkCrmCustomerService;
import org.springframework.stereotype.Service;

/**
 * 客户表(WkCrmCustomer)表服务实现类
 *
 * @author makejava
 * @since 2022-04-08 14:39:52
 */
@Service("wkCrmCustomerService")
public class WkCrmCustomerServiceImpl extends ServiceImpl<WkCrmCustomerDao, WkCrmCustomer> implements WkCrmCustomerService {

}

