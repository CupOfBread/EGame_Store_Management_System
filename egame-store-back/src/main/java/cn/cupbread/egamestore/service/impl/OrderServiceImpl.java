package cn.cupbread.egamestore.service.impl;

import cn.cupbread.egamestore.entity.Order;
import cn.cupbread.egamestore.mapper.OrderMapper;
import cn.cupbread.egamestore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
