package com.cheese.order_manage_system.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 订单
 *
 * @author July
 * @TableName order_table
 */
@TableName(value = "order_table")
@Data
public class OrderTable implements Serializable {
    /**
     * 订单编号
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 订单时间
     */
    @TableField(exist = false)
    private String orderTime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 是否删除0是不删除
     */
    @TableLogic
    private Integer isdelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}