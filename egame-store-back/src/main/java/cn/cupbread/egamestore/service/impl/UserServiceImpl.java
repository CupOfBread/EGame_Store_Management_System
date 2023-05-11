package cn.cupbread.egamestore.service.impl;

import cn.cupbread.egamestore.entity.User;
import cn.cupbread.egamestore.mapper.UserMapper;
import cn.cupbread.egamestore.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User queryByUserName(String name) {
        return this.query().eq("user_name", name).one();
    }
}
