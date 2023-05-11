package cn.cupbread.egamestore.controller;

import cn.cupbread.egamestore.common.vo.Result;
import cn.cupbread.egamestore.entity.Game;
import cn.cupbread.egamestore.entity.GameConsole;
import cn.cupbread.egamestore.service.IGameConsoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 游戏机 前端控制器
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/console")
public class GameConsoleController {

    @Autowired
    private IGameConsoleService consoleService;

    @GetMapping("/all")
    public Result<?> getGameAssetsAll() {
        return Result.OK(consoleService.list());
    }

    @PostMapping("/add")
    public Result<?> addGame(@RequestBody GameConsole console) {
        consoleService.saveOrUpdate(console);
        return Result.OK();
    }

    @PostMapping("/del")
    public Result<?> delGame(@RequestParam(name = "id") Integer id) {
        GameConsole gameConsole = consoleService.getById(id);
        if (gameConsole != null) {
            consoleService.removeById(gameConsole);
        }
        return Result.OK();
    }
}
