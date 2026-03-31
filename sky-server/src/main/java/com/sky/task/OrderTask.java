package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 每分钟执行一次，处理超时订单
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void processTimeoutOrders() {
        log.info("开始处理超时订单...");
        List<Orders> ordersList = orderMapper
                .getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        if(ordersList != null && !ordersList.isEmpty()) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时未支付，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
                log.info("订单{}已超时，已取消", orders.getNumber());
            }
        } else {
            log.info("没有需要处理的超时订单");
        }
        log.info("完成处理超时订单");
    }

    /**
     * 每天凌晨1点执行一次，处理配送订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrders() {
        log.info("开始处理配送订单...");
        List<Orders> ordersList = orderMapper
                .getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusMinutes(60));
        if(ordersList != null && !ordersList.isEmpty()) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
                log.info("订单{}已配送完成，已更新状态", orders.getNumber());
            }
        } else {
            log.info("没有需要处理的配送订单");
        }
        log.info("完成处理配送订单");
    }

}
