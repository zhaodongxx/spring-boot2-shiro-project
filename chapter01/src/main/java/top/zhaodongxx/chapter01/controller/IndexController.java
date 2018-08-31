package top.zhaodongxx.chapter01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhaodongxx.chapter01.result.Result;
import top.zhaodongxx.chapter01.result.ResultGenerator;

/**
 * <P></P>
 *
 * @author zhaodong zhaodongxx@outlook.com
 * @version v1.0
 * @since 2018/3/30 21:55
 */
@RestController
public class IndexController {

    @GetMapping("/helloworld")
    public Result helloWorld() {

        return ResultGenerator.genSuccessResult("helloworld");
    }
}
