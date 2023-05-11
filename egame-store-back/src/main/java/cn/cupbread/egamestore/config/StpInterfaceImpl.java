package cn.cupbread.egamestore.config;

import cn.cupbread.egamestore.entity.User;
import cn.cupbread.egamestore.service.IUserService;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private IUserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userService.getById(loginId.toString());
        List<String> list = new ArrayList<String>();
        list.add(user.getRole());
        return list;
    }
}
