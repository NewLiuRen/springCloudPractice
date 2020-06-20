package com.demo.service.impl;

import com.demo.client.AuthClient;
import com.demo.pojo.User;
import com.demo.pojo.UserInfo;
import com.demo.properties.JwtProperties;
import com.demo.service.IAuthService;
import com.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthClient authClient;
    @Autowired
    private JwtProperties prop;

    @Override
    public String authentication(String username, String password) {
        try {
            User user = this.authClient.findUser(username, password);

            if (user == null) return null;

            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()), prop.getPrivateKey(), prop.getExpire());

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
