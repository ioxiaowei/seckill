package io.xiaowei.user.resource;

import io.xiaowei.model.UserModel;
import io.xiaowei.user.req.UserRegisterReq;
import io.xiaowei.user.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("user")
public class UserResource {

    @Resource
    private IUserService iUserService;

    @GetMapping("/find/{id}")
    public UserModel findById(@PathVariable(value = "id") Long id) {
        return iUserService.findById(id);
    }

    @PostMapping(value = "register")
    public UserModel registerUser(@RequestBody UserRegisterReq userRegisterReq) {
        return iUserService.registerUser(userRegisterReq);
    }

}
