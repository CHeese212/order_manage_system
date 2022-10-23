package com.cheese.order_manage_system.service.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheese.order_manage_system.constant.OrderConstant;
import com.cheese.order_manage_system.pojo.OrderTable;
import com.cheese.order_manage_system.service.OrderTableService;
import com.cheese.order_manage_system.mapper.OrderTableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
* @author 86133
* @description 针对表【order_table(订单)】的数据库操作Service实现
* @createDate 2022-10-19 13:01:03
*/
@Service
public class OrderTableServiceImpl extends ServiceImpl<OrderTableMapper, OrderTable> implements OrderTableService{

    @Resource
    private OrderTableMapper orderTableMapper;

    @Override
    public List<OrderTable> getOrdersByCommodityName(HttpServletRequest request, String commodityName, int pageNum) {
        QueryWrapper<OrderTable> orderTableQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(commodityName)) {
            orderTableQueryWrapper.like("commodity_name", commodityName);
        }
        Page<OrderTable> page = new Page<>(pageNum, OrderConstant.PAGE_SIZE);
        Page<OrderTable> orderPage = orderTableMapper.selectPage(page, orderTableQueryWrapper);
        request.getSession().setAttribute(OrderConstant.ORDER_PAGE, orderPage);
        List<OrderTable> orderTables = orderPage.getRecords();
        return orderTables.stream().map(this::getSafetyOrder).collect(Collectors.toList());
    }

    @Override
    public OrderTable getOrderById(Long id) {
        if (id == null || id < 0) {
            return new OrderTable();
        }
        QueryWrapper<OrderTable> orderTableQueryWrapper = new QueryWrapper<>();
        orderTableQueryWrapper.eq("id", id);
        OrderTable order = orderTableMapper.selectOne(orderTableQueryWrapper);
        if (order == null) {
            order = new OrderTable();
        }
        return getSafetyOrder(order);
    }

    @Override
    public List<OrderTable> getOrdersByBuyer(HttpServletRequest request, String buyer, int pageNum) {
        if (StringUtils.isBlank(buyer)) {
            return new ArrayList<>();
        }
        Page<OrderTable> orderPage = new Page<>(pageNum, OrderConstant.PAGE_SIZE);
        QueryWrapper<OrderTable> orderTableQueryWrapper = new QueryWrapper<>();
        orderTableQueryWrapper.like("buyer", buyer);
        orderPage = orderTableMapper.selectPage(orderPage, orderTableQueryWrapper);
        request.getSession().setAttribute(OrderConstant.ORDER_PAGE, orderPage);
        List<OrderTable> orderTables = orderPage.getRecords();
        return orderTables.stream().map(this::getSafetyOrder).collect(Collectors.toList());
    }

    @Override
    public Boolean addOrder(String commodityName, Integer orderNumber, BigDecimal commodityPrice, String seller, String buyer) {
        if (StringUtils.isAnyBlank(commodityName, seller, buyer) || orderNumber == null || commodityPrice == null) {
            return false;
        }
        OrderTable order = new OrderTable();
        order.setCommodityName(commodityName);
        order.setOrderNumber(orderNumber);
        order.setCommodityPrice(commodityPrice);
        order.setSeller(seller);
        order.setBuyer(buyer);
        order.setTotalPrice(commodityPrice.multiply(new BigDecimal(orderNumber.toString())));
        int result = orderTableMapper.insert(order);
        return result != -1;
    }

    @Override
    public Boolean deleteOrderById(Long id) {
        if (id == null) {
            return false;
        }
        int result = orderTableMapper.deleteById(id);
        return result != -1;
    }

    @Override
    public Boolean updateOrder(OrderTable order) {
        if (StringUtils.isAnyBlank(order.getCommodityName(), order.getSeller(), order.getBuyer()) || order.getId() == null || order.getCommodityPrice() == null || order.getOrderNumber() == null) {
            return false;
        }
        order.setTotalPrice(order.getCommodityPrice().multiply(new BigDecimal(order.getOrderNumber().toString())));
        int result = orderTableMapper.updateById(order);
        return result != -1;
    }

    //获取安全的订单信息
    public OrderTable getSafetyOrder(OrderTable order) {
        if (order == null) {
            return null;
        }
        OrderTable safetyOrder = new OrderTable();
        safetyOrder.setId(order.getId());
        safetyOrder.setCommodityName(order.getCommodityName());
        safetyOrder.setOrderNumber(order.getOrderNumber());
        safetyOrder.setCommodityPrice(order.getCommodityPrice());
        safetyOrder.setSeller(order.getSeller());
        safetyOrder.setBuyer(order.getBuyer());
        safetyOrder.setTotalPrice(order.getTotalPrice());
        Date createTime = order.getCreatetime();
        Date updateTime = order.getUpdatetime();
        Date orderTime = createTime;
        if (createTime != null && updateTime != null && !createTime.equals(updateTime)) {
            orderTime = updateTime;
        }
        if (orderTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss", Locale.CHINA);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia / Chongqing"));//不知道为啥
            safetyOrder.setOrderTime(simpleDateFormat.format(orderTime));
        }
        return safetyOrder;
    }
}




