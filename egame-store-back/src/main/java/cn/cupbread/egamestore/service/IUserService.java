package cn.cupbread.egamestore.service;

import cn.cupbread.egamestore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
public interface IUserService extends IService<User> {

    User queryByUserName(String name);

}
