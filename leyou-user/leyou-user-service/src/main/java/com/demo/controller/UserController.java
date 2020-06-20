package com.demo.controller;

import com.demo.pojo.User;
import com.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询用户是否不存在
     * @param data 检查的数据，根据类型而不同
     * @param type 查询类型 1：用户名  2：电话
     * @return 查询状态，是否存在（true：不存在， false：存在），或参数错误
     */
    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data, @PathVariable("type") Integer type) {
        Boolean flag = this.userService.checkData(data, type);
        if (flag == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(flag);
    }

    @PostMapping("register")
    public ResponseEntity<Boolean> register(@Valid User user) {
        Boolean flag = this.userService.register(user);
        if (flag == null || !flag) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("findUser")
    public ResponseEntity<User> findUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = this.userService.findUser(username, password);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }
}
