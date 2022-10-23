create table order_table
(
    id bigint auto_increment comment '订单编号',
    commodity_name  varchar(256) null comment '商品名称',
    order_number  int default 1 null comment '订购数量',
    commodity_price decimal(10,1) not null comment '商品单价',
    seller varchar(256) null comment '卖家',
    buyer varchar(256) null comment '买家',
    total_price decimal(10,1) null comment '总价',
    createTime   datetime  default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime  default CURRENT_TIMESTAMP null on update  CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint   default 0                 null comment '是否删除0是不删除',
    constraint order_pk
        primary key (id)
)
    comment '订单';