package com.jychan.notbad.service;

import com.jychan.notbad.domain.UserInfo;
import com.jychan.notbad.mapper.RoleInfoMapper;
import com.jychan.notbad.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenjinying on 2017/5/13.
 * mail: 415683089@qq.com
 */
@Service
public class AccountService {

    @Autowired
    RoleInfoMapper roleInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    public UserInfo login(String account, String password) {
        return userInfoMapper.findUserInfo(account, password);
    }

    public String getToken(String account) {
        // TODO: 2017/5/13 token 考虑使用对象
        return "hello token";
    }

    public boolean register(String account, String nickname, String email, String phone, String password) {
        UserInfo userInfo = new UserInfo(account, nickname, email, phone);

        try {
            // FIXME: 2017/5/14 事务
            userInfoMapper.addUserPermission(account, password);
            userInfoMapper.addUserInfo(userInfo);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
