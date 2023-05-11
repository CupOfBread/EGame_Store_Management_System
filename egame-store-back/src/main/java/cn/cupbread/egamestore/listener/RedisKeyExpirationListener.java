package cn.cupbread.egamestore.listener;

import cn.cupbread.egamestore.entity.AssetsConsole;
import cn.cupbread.egamestore.entity.GameConsole;
import cn.cupbread.egamestore.entity.Order;
import cn.cupbread.egamestore.service.IAssetsConsoleService;
import cn.cupbread.egamestore.service.IGameConsoleService;
import cn.cupbread.egamestore.service.IOrderService;
import cn.cupbread.egamestore.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private final Logger logger = LoggerFactory.getLogger(RedisKeyExpirationListener.class);

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGameConsoleService gameConsoleService;
    @Autowired
    private IAssetsConsoleService assetsConsoleService;
    @Autowired
    private RedissonClient redissonClient;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 订单过期
        if (message.toString().startsWith("ORDER_EXP")) {
            String orderId = message.toString().substring(message.toString().lastIndexOf("_") + 1);
            logger.info("订单过期: " + orderId);
            RLock orderLock = redissonClient.getFairLock("ORDER_" + orderId);
            try {
                orderLock.lock();
                Order order = orderService.getById(orderId);
                if (order == null || order.getPaid() == 1)
                    return;
                order.setFinished(2);
                orderService.updateById(order);

                AssetsConsole console = assetsConsoleService.getById(order.getConsoleAsset());
                assetsConsoleService.updateById(console.setStatus(0));

            } finally {
                orderLock.unlock();
            }
            return;
        }


    }
}
