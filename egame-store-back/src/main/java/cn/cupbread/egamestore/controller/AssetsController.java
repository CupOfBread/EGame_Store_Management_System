package cn.cupbread.egamestore.controller;

import cn.cupbread.egamestore.common.vo.Result;
import cn.cupbread.egamestore.entity.AssetsConsole;
import cn.cupbread.egamestore.entity.AssetsGame;
import cn.cupbread.egamestore.entity.Game;
import cn.cupbread.egamestore.service.IAssetsConsoleService;
import cn.cupbread.egamestore.service.IAssetsGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资产 前端控制器
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private IAssetsGameService assetsGameService;

    @Autowired
    private IAssetsConsoleService assetsConsoleService;

    @GetMapping("/game/all")
    public Result<?> getGameAssetsAll() {
        return Result.OK(assetsGameService.list());
    }

    @PostMapping("/game/add")
    public Result<?> addGameAsset(@RequestBody AssetsGame assetsGame) {
        assetsGameService.saveOrUpdate(assetsGame);
        return Result.OK();
    }

    @PostMapping("/game/del")
    public Result<?> delGameAsset(@RequestParam(name = "id") Integer id) {
        AssetsGame assetsGame = assetsGameService.getById(id);
        if (assetsGame != null) {
            assetsGameService.removeById(assetsGame);
        }
        return Result.OK();
    }


    @GetMapping("/console/all")
    public Result<?> getConsoleAssetsAll() {
        return Result.OK(assetsConsoleService.list());
    }

    @PostMapping("/console/add")
    public Result<?> addGameConsole(@RequestBody AssetsConsole assetsConsole) {
        assetsConsoleService.saveOrUpdate(assetsConsole);
        return Result.OK();
    }

    @PostMapping("/console/del")
    public Result<?> delGameConsole(@RequestParam(name = "id") Integer id) {
        AssetsConsole assetsConsole = assetsConsoleService.getById(id);
        if (assetsConsole != null) {
            assetsConsoleService.removeById(assetsConsole);
        }
        return Result.OK();
    }

}
