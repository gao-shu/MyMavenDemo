package demotest1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demotest1.entity.WkCrmCustomer;
import demotest1.service.WkCrmCustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 客户表(WkCrmCustomer)表控制层
 *
 * @author makejava
 * @since 2022-04-08 14:39:51
 */
@RestController
@RequestMapping("wkCrmCustomer")
public class WkCrmCustomerController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WkCrmCustomerService wkCrmCustomerService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param wkCrmCustomer 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<WkCrmCustomer> page, WkCrmCustomer wkCrmCustomer) {
        return success(this.wkCrmCustomerService.page(page, new QueryWrapper<>(wkCrmCustomer)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.wkCrmCustomerService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param wkCrmCustomer 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody WkCrmCustomer wkCrmCustomer) {
        return success(this.wkCrmCustomerService.save(wkCrmCustomer));
    }

    /**
     * 修改数据
     *
     * @param wkCrmCustomer 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody WkCrmCustomer wkCrmCustomer) {
        return success(this.wkCrmCustomerService.updateById(wkCrmCustomer));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.wkCrmCustomerService.removeByIds(idList));
    }
}

