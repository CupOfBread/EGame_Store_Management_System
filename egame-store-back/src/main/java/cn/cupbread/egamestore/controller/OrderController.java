package cn.cupbread.egamestore.controller;

import cn.cupbread.egamestore.common.vo.Result;
import cn.cupbread.egamestore.entity.AssetsConsole;
import cn.cupbread.egamestore.entity.GameConsole;
import cn.cupbread.egamestore.entity.Order;
import cn.cupbread.egamestore.service.*;
import cn.cupbread.egamestore.util.RedisUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;


/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IGameService gameService;
    @Autowired
    private IGameConsoleService gameConsoleService;
    @Autowired
    private IAssetsConsoleService assetsConsoleService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/all")
    public Result<?> getOrderAll() {
        return Result.OK(orderService.list());
    }

    @GetMapping("/page")
    public Result<?> getOrderPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                  @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize) {
        Page<Order> page = new Page<>(pageNo, pageSize);
        return Result.OK(orderService.page(page));
    }

    @PostMapping("/new")
    public Result<?> newOrder(@RequestParam(name = "consoleId") Integer consoleId,
                              @RequestParam(name = "customerPhone") String customerPhone,
                              @RequestParam(name = "leaseTime") Integer leaseTime
    ) {
        if (customerPhone.length() != 11)
            return Result.error(400, "手机号有误");

        RLock consoleLock = redissonClient.getFairLock("ORDER_ASSET_GAME_CONSOLE" + consoleId);
        Order order = new Order();
        try {
            consoleLock.lock();
            AssetsConsole assetsConsole = assetsConsoleService.getById(consoleId);

            if (assetsConsole == null)
                return Result.error(404, "游戏设备未找到");
            if (assetsConsole.getStatus() != 0)
                return Result.error(400, "游戏设备已被租出");

            BigDecimal amount = assetsConsole.getUnitPrice().multiply(BigDecimal.valueOf(leaseTime / 60));

            order.setConsoleAsset(consoleId)
                    .setCustomerPhone(customerPhone)
                    .setLeaseTime(leaseTime)
                    .setAmount(amount)
                    .setCreateTime(new Date());
            orderService.save(order);

            assetsConsole.setStatus(1);
            assetsConsoleService.updateById(assetsConsole);

            redisUtil.set("ORDER_EXP_" + order.getId(), "", 180);

        } finally {
            consoleLock.unlock();
        }

        return Result.OK(order);
    }


}
