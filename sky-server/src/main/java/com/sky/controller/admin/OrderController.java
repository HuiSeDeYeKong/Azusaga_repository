package com.sky.controller.admin;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "订单相关接口")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 订单条件查询接口
     *
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("value = 订单条件查询接口")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        log.info("商家按条件查询订单");
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 各状态订单数量统计接口
     *
     * @return
     */
    @GetMapping("/statistics")
    @ApiOperation("value = 各状态订单数量统计接口")
    public Result<OrderStatisticsVO> statistics() {
        log.info("商家查询各状态订单数量统计数据");
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * 订单详情查询接口
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public Result<OrderVO> getOrderDetails(@PathVariable Long id) {
        log.info("商家查询订单详情，订单id：{}", id);
        OrderVO orderVO = orderService.getOrderDetails(id);
        return Result.success(orderVO);
    }

    /**
     * 商家确认订单接口
     * @param ordersConfirmDTO
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("value = 商家确认订单接口")
    public Result confirmOrder(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        log.info("商家确认订单，订单id：{}", ordersConfirmDTO.getId());
        orderService.confirmOrder(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 商家拒绝订单接口
     * @param ordersRejectionDTO
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("value = 商家拒绝订单接口")
    public Result rejectOrder(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        log.info("商家拒绝订单，订单id：{}", ordersRejectionDTO.getId());
        orderService.rejectOrder(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 商家取消订单接口
     * @param ordersCancelDTO
     * @return
     * @throws Exception
     */
    @PutMapping("/cancel")
    @ApiOperation("value = 商家取消订单接口")
    public Result cancelOrder(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        log.info("商家取消订单，订单id：{}", ordersCancelDTO.getId());
        orderService.cancelOrder(ordersCancelDTO);
        return Result.success();
     }

    /**
     * 商家派送订单接口
     * @param id
     * @return
     */
     @PutMapping("/delivery/{id}")
     @ApiOperation("value = 商家派送订单接口")
     public Result deliveryOrder(@PathVariable Long id) {
         log.info("商家派送订单，订单id：{}", id);
         orderService.deliveryOrder(id);
         return Result.success();
     }

     @PutMapping("/complete/{id}")
        @ApiOperation("value = 商家完成订单接口")
     public Result completeOrder(@PathVariable Long id) {
         log.info("商家完成订单，订单id：{}", id);
         orderService.completeOrder(id);
         return Result.success();
     }

}
