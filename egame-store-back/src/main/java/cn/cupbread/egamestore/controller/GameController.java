package cn.cupbread.egamestore.controller;

import cn.cupbread.egamestore.common.vo.Result;
import cn.cupbread.egamestore.entity.AssetsGame;
import cn.cupbread.egamestore.entity.Game;
import cn.cupbread.egamestore.entity.GameType;
import cn.cupbread.egamestore.service.IGameService;
import cn.cupbread.egamestore.service.IGameTypeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 游戏 前端控制器
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private IGameService gameService;
    @Autowired
    private IGameTypeService gameTypeService;

    @GetMapping("/all")
    public Result<?> getGameAll() {
        return Result.OK(gameService.list());
    }

    @GetMapping("/page")
    public Result<?> getGamePage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize) {
        Page<Game> page = new Page<>(pageNo, pageSize);
        return Result.OK(gameService.page(page));
    }

    @GetMapping("/type/all")
    public Result<?> getGameTypeAll() {
        return Result.OK(gameTypeService.list());
    }

    @PostMapping("/type/add")
    public Result<?> addGameType(@RequestBody GameType gameType) {
        gameTypeService.saveOrUpdate(gameType);
        return Result.OK();
    }

    @PostMapping("/add")
    public Result<?> addGame(@RequestBody Game game) {
        gameService.saveOrUpdate(game);
        return Result.OK();
    }

    @PostMapping("/del")
    public Result<?> delGame(@RequestParam(name = "id") Integer id) {
        Game game = gameService.getById(id);
        if (game != null) {
            gameService.removeById(game);
        }
        return Result.OK();
    }

    @PostMapping("/type/del")
    public Result<?> delGameType(@RequestParam(name = "id") Integer id) {
        GameType gameType = gameTypeService.getById(id);
        if (gameType != null) {
            gameTypeService.removeById(gameType);
        }
        return Result.OK();
    }
}
