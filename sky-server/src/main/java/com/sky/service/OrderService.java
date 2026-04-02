package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.*;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface OrderService {

    /**
     * 用户提交订单
     *
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     *
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 历史订单查询
     *
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult pageQueryForUser(int page, int pageSize, Integer status);

    /**
     * 订单详情查询
     *
     * @param id
     * @return
     */
    OrderVO getOrderDetails(Long id);

    /**
     * 用户取消订单
     *
     * @param id
     */
    void userCancelById(Long id) throws Exception;

    /**
     * 用户再次购买
     *
     * @param id
     */
    void repetition(Long id);

    /**
     * 订单条件查询
     *
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各状态订单数量统计
     *
     * @return
     */
    OrderStatisticsVO statistics();

    /**
     * 商家确认订单
     *
     * @param ordersConfirmDTO
     */
    void confirmOrder(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 商家拒绝订单
     *
     * @param ordersRejectionDTO
     */
    void rejectOrder(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 商家取消订单
     *
     * @param ordersCancelDTO
     */
    void cancelOrder(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 商家配送订单
     *
     * @param id
     */
    void deliveryOrder(Long id);

    /**
     * 商家完成订单
     *
     * @param id
     */
    void completeOrder(Long id);

    /**
     * 客户催单
     *
     * @param id
     */
    void reminder(Long id);


}
