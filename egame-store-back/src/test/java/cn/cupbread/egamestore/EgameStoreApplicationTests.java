package cn.cupbread.egamestore;

import cn.cupbread.egamestore.entity.AssetsConsole;
import cn.cupbread.egamestore.entity.AssetsGame;
import cn.cupbread.egamestore.entity.Game;
import cn.cupbread.egamestore.entity.GameType;
import cn.cupbread.egamestore.service.IAssetsConsoleService;
import cn.cupbread.egamestore.service.IAssetsGameService;
import cn.cupbread.egamestore.service.IGameService;
import cn.cupbread.egamestore.service.IGameTypeService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class EgameStoreApplicationTests {

    @Autowired
    private IGameTypeService gameTypeService;
    @Autowired
    private IGameService gameService;
    @Autowired
    private IAssetsGameService assetsGameService;
    @Autowired
    private IAssetsConsoleService assetsConsoleService;

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://121.4.115.91:20000/egame_store", "root", "d7fe7366ee4f3972")
                .globalConfig(builder -> {
                    builder.author("CupOfBread") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://demoo"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.cupbread.egamestore"); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix("e_")
                            .entityBuilder().enableLombok().enableChainModel().disableSerialVersionUID().idType(IdType.ASSIGN_ID); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void test1() {
        System.out.println(SecureUtil.md5("pass"));
    }

}
