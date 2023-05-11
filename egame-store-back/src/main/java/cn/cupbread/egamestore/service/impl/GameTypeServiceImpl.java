package cn.cupbread.egamestore.service.impl;

import cn.cupbread.egamestore.entity.GameType;
import cn.cupbread.egamestore.mapper.GameTypeMapper;
import cn.cupbread.egamestore.service.IGameTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游戏类型 服务实现类
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@Service
public class GameTypeServiceImpl extends ServiceImpl<GameTypeMapper, GameType> implements IGameTypeService {

}
