package com.cheese.order_manage_system.pojo.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 添加订单请求类
 *
 * @author July
 */
@Data
public class OrderAddRequest implements Serializable {

    private static final long serialVersionUID = -3686925393040124434L;
    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 订购数量
     */
    private Integer orderNumber;

    /**
     * 商品单价
     */
    private BigDecimal commodityPrice;

    /**
     * 卖家
     */
    private String seller;

    /**
     * 买家
     */
    private String buyer;
}
