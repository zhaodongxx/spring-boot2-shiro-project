package top.zhaodongxx.chapter02.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhaodongxx.chapter02.domain.User;
import top.zhaodongxx.chapter02.result.Result;
import top.zhaodongxx.chapter02.result.ResultGenerator;
import top.zhaodongxx.chapter02.service.ShiroService;

import javax.annotation.Resource;

/**
 * @author zhaodong zhaodongxx@outlook.com
 * @version v1.0
 * @since 2018/7/19 10:53
 */
@RestController
public class RegisterController {

    @Resource
    private ShiroService shiroService;

    @PostMapping("/register")
    public Result register(String username, String password) {

        User user = shiroService.addUser(username, password);
        return ResultGenerator.genSuccessResult(user).setMessage("注册成功");
    }
}
