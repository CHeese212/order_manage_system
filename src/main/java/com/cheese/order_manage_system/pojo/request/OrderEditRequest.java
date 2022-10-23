package com.cheese.order_manage_system.pojo.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 修改订单请求类
 *
 * @author cheese
 */
@Data
public class OrderEditRequest implements Serializable {

    private static final long serialVersionUID = -6461848875689613158L;
    /**
     * 订单编号
     */
    private Long id;

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
