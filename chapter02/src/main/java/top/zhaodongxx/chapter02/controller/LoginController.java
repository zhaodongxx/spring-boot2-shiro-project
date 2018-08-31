package top.zhaodongxx.chapter02.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhaodongxx.chapter02.result.Result;
import top.zhaodongxx.chapter02.result.ResultGenerator;

/**
 *
 * @author zhaodong
 * @version v1.0
 * @email zhaodongxx@outlook.com
 * @since 2018/3/30 23:05
 */
@RestController
public class LoginController {
    @PostMapping("/doLogin")
    public Result doLogin(String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return ResultGenerator.genFailResult("登录失败，用户名或密码错误！");
        }
        return ResultGenerator.genSuccessResult("登录成功");
    }
}
