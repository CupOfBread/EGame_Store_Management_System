package cn.cupbread.egamestore.controller;

import cn.cupbread.egamestore.common.vo.Result;
import cn.cupbread.egamestore.entity.User;
import cn.cupbread.egamestore.service.IUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/get/all")
    public Result<?> getUserAll(){
        return Result.OK(userService.list());
    }

    @PostMapping("/login")
    public Result<?> login(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password) {
        User user = userService.queryByUserName(username);
        if (user == null)
            return Result.error(404, "用户未找到");
        if (user.getPassword().equals(SecureUtil.md5(password)))
            StpUtil.login(user.getId());
        else
            return Result.error(400, "用户名或密码错误");

        JSONObject res = new JSONObject();
        res.put("realName", user.getUserName());
        res.put("token", StpUtil.getTokenValue());
        res.put("username", user.getUserName());

        JSONObject role = new JSONObject();
        role.put("roleName", user.getRole());

        JSONArray roles = new JSONArray();
        roles.add(role);

        res.put("roles", roles);
        return Result.OK(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if (userService.query().eq("name", user.getUserName()).count() > 0)
            return Result.error("用户名已存在");
        if (user.getPassword().length() < 6)
            return Result.error("密码需大于六位数");
        user.setRole("user").setCreateTime(new Date());
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.save(user);
        return Result.OK("", "success");
    }

    @GetMapping("/info")
    public Result<?> info() {
        User user = userService.getById(StpUtil.getLoginId().toString());
        if (user == null)
            return Result.error(404, "用户未找到");
        JSONObject res = new JSONObject();
        res.put("realName", user.getUserName());
        res.put("token", StpUtil.getTokenValue());
        res.put("username", user.getUserName());

        JSONObject role = new JSONObject();
        role.put("roleName", user.getRole());
        role.put("value", user.getRole());

        JSONArray roles = new JSONArray();
        roles.add(role);

        res.put("roles", roles);
        return Result.OK(res);
    }

}
