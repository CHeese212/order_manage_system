package com.cheese.order_manage_system.controller;

import com.cheese.order_manage_system.constant.OrderConstant;
import com.cheese.order_manage_system.pojo.OrderTable;
import com.cheese.order_manage_system.pojo.request.OrderAddRequest;
import com.cheese.order_manage_system.pojo.request.OrderEditRequest;
import com.cheese.order_manage_system.service.OrderTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class OrderController {
    @Resource
    OrderTableService orderTableService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<OrderTable> orders = orderTableService.getOrdersByCommodityName(request, null, 1);
        request.getSession().setAttribute(OrderConstant.ORDER_LIST, orders);
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add_order";
    }

    @GetMapping("/edit")
    public String edit(HttpServletRequest request, Long id) {
        OrderTable order = orderTableService.getOrderById(id);
        request.setAttribute("order", order);
        return "edit_order";
    }

    @GetMapping( "/searchByName")
    public String searchByCommodityName(HttpServletRequest request, @RequestParam(required = false) String commodityName,Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        List<OrderTable> orders = orderTableService.getOrdersByCommodityName(request, commodityName, pageNum);
        request.getSession().setAttribute(OrderConstant.ORDER_LIST, orders);
        return "index";
    }

    @GetMapping("/searchById")
    public String searchById(HttpServletRequest request, Long id) {
        OrderTable order = orderTableService.getOrderById(id);
        List<OrderTable> orders = new ArrayList<>();
        orders.add(order);
        request.getSession().setAttribute(OrderConstant.ORDER_LIST, orders);
        return "index";
    }

    @GetMapping("/searchByBuyer")
    public String searchByBuyer(HttpServletRequest request, String buyer, Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        List<OrderTable> orders = orderTableService.getOrdersByBuyer(request, buyer, pageNum);
        request.getSession().setAttribute(OrderConstant.ORDER_LIST, orders);
        return "index";
    }

    @PostMapping("/add")
    public String addOrder(OrderAddRequest orderAddRequest) {
        String commodityName = orderAddRequest.getCommodityName();
        Integer orderNumber = orderAddRequest.getOrderNumber();
        BigDecimal commodityPrice = orderAddRequest.getCommodityPrice();
        String seller = orderAddRequest.getSeller();
        String buyer = orderAddRequest.getBuyer();
        if (StringUtils.isAnyBlank(commodityName, seller, buyer) || orderNumber == null || commodityPrice == null) {
            return "redirect:";
        }
        orderTableService.addOrder(commodityName, orderNumber, commodityPrice, seller, buyer);
        return "redirect:";
    }

    @GetMapping("/delete")
    public String deleteOrder(Long id) {
        if (id == null) {
            return "redirect:";
        }
        orderTableService.deleteOrderById(id);
        return "redirect:";
    }

    @PostMapping("/edit")
    public String editOrder(OrderEditRequest orderEditRequest) {
        Long id = orderEditRequest.getId();
        String commodityName = orderEditRequest.getCommodityName();
        Integer orderNumber = orderEditRequest.getOrderNumber();
        BigDecimal commodityPrice = orderEditRequest.getCommodityPrice();
        String seller = orderEditRequest.getSeller();
        String buyer = orderEditRequest.getBuyer();
        if (StringUtils.isAnyBlank(commodityName, seller, buyer) || id == null || orderNumber == null || commodityPrice == null) {
            return "redirect:";
        }
        OrderTable order = new OrderTable();
        order.setId(id);
        order.setCommodityName(commodityName);
        order.setOrderNumber(orderNumber);
        order.setCommodityPrice(commodityPrice);
        order.setSeller(seller);
        order.setBuyer(buyer);
        orderTableService.updateOrder(order);
        return "redirect:";
    }
}
