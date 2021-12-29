package com.demo;

import com.demo.pojo.UserInfo;
import com.demo.utils.JwtUtils;
import com.demo.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class AuthTest {
    private static final String pubKeyPath = "E:\\IDEAProject\\leyou\\key\\rsa.pub";

    private static final String priKeyPath = "E:\\IDEAProject\\leyou\\key\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(34L, "lisi"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MzQsInVzZXJuYW1lIjoibGlzaSIsImV4cCI6MTU5MjU0ODgxNn0.ZtCQSyY7_hz7iv-a6wiEUQ0zvURTXwfi3uJ7DWmPMqjR1s9BjmhWDJkFajpZlb1VosycAfwRwTYLfuKBkJ2zlWxK5HJZf0dp6yPL9GniHR8s_ZfvPvSKMVkJ-vCHKX5hx1zKYiv4DpNmZPfqlc5ExWfxiDFDgsSlpgCZL5aJpe8";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
