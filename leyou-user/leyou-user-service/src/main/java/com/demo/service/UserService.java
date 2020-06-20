package com.demo.service;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.utils.CodecUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Boolean checkData(String data, Integer type) {
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(user) == 0;
    }

    public Boolean register(User user) {
        // 校验用户是否存在
        Boolean flag = this.checkData(user.getUsername(), 1);
        if (!flag) return Boolean.FALSE;

        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));

        user.setId(null);
        user.setCreated(new Date());

        return this.userMapper.insert(user) == 1;
    }

    public User findUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);

        if (user == null ||
                !user.getPassword().equals(CodecUtils.md5Hex(password, user.getSalt()))
        ) return null;

        return user;
    }
}
