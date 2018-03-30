package top.zhaodongxx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhaodongxx.result.Result;
import top.zhaodongxx.result.ResultGenerator;

/**
 * <P></P>
 *
 * @author zhaodong
 * @version v1.0
 * @email zhaodongxx@outlook.com
 * @since 2018/3/30 21:55
 */
@RestController
public class IndexController {

    @GetMapping("/helloworld")
    public Result helloWorld() {

        return ResultGenerator.genSuccessResult("helloworld");
    }
}
