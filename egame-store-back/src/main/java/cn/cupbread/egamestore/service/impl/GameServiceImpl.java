package cn.cupbread.egamestore.service.impl;

import cn.cupbread.egamestore.entity.Game;
import cn.cupbread.egamestore.mapper.GameMapper;
import cn.cupbread.egamestore.service.IGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游戏 服务实现类
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements IGameService {

}
