package com.cheese.order_manage_system.service;

import com.cheese.order_manage_system.pojo.OrderTable;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author cheese
 * @description 针对表【order_table(订单)】的数据库操作Service
 * @createDate 2022-10-19 13:01:03
 */
public interface OrderTableService extends IService<OrderTable> {
    /**
     * 根据商品名称查询订单列表
     *
     * @return 返回匹配的列表，没找到就返回一个空列表
     * @author cheese
     */
    List<OrderTable> getOrdersByCommodityName(HttpServletRequest request, String commodityName, int pageNum);

    /**
     * 根据商品id查询订单
     *
     * @param id
     * @return 返回id对应的Order对象，没有就返回null
     * @author cheese
     */
    OrderTable getOrderById(Long id);

    /**
     * 根据买家姓名查询订单列表
     *
     * @param buyer 买家
     * @return 返回匹配的列表，没找到就返回一个空列表
     * @author cheese
     */
    List<OrderTable> getOrdersByBuyer(HttpServletRequest request, String buyer, int pageNum);

    /**
     * 添加订单
     *
     * @param commodityName  商品名称
     * @param orderNumber    订单数量
     * @param commodityPrice 商品单价
     * @param seller         卖家
     * @param buyer          买家
     * @return boolean表示是否添加成功
     * @author cheese
     */
    Boolean addOrder(String commodityName, Integer orderNumber, BigDecimal commodityPrice, String seller, String buyer);

    /**
     * 根据商品id删除订单
     *
     * @param id 商品id
     * @return boolean表示是否删除成功
     * @author cheese
     */
    Boolean deleteOrderById(Long id);

    /**
     * 根据id修改订单
     * @param order 修改后的订单对象
     * @return boolean表示是否删除成功
     * @author cheese
     */
    Boolean updateOrder(OrderTable order);
}
