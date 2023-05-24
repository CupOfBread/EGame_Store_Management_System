package cn.cupbread.egamestore.controller;

import cn.cupbread.egamestore.common.vo.Result;
import cn.cupbread.egamestore.entity.AssetsConsole;
import cn.cupbread.egamestore.entity.AssetsGame;
import cn.cupbread.egamestore.entity.Order;
import cn.cupbread.egamestore.service.IAssetsConsoleService;
import cn.cupbread.egamestore.service.IAssetsGameService;
import cn.cupbread.egamestore.service.IOrderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 仪表盘
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private IAssetsGameService assetsGameService;
    @Autowired
    private IAssetsConsoleService assetsConsoleService;
    @Autowired
    private IOrderService orderService;

    @GetMapping("/index")
    public Result<?> getIndexInfo() {

        JSONObject res = new JSONObject();

        Double amount = 0.0;
        for (Order order : orderService.query().eq("finished", 1).list()) {
            amount += order.getAmount().doubleValue();
        }
        res.put("amount", amount);


        res.put("gameCount", assetsGameService.count());

        return Result.OK(res);
    }


}
