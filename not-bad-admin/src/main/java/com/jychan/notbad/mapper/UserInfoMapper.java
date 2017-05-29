package com.jychan.notbad.mapper;

import com.jychan.notbad.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by chenjinying on 2017/5/13.
 * mail: 415683089@qq.com
 */
public interface UserInfoMapper {

    @Select("select a.id, a.account, a.nickname, a.email, a.phone, a.ctime, a.utime " +
            "from user_info a, user_permission b " +
            "where a.account = b.account and " +
            "b.password = #{password} and (a.account = #{account} " +
            "or a.email = #{account} or a.phone = #{account}) " +
            "limit 1 ")
    UserInfo findUserInfo(@Param("account") String account, @Param("password") String password);

    @Insert("insert into user_permission (account, password) values (#{account}, #{password}) ")
    int addUserPermission(@Param("account") String account, @Param("password") String password);

    @Insert("insert into user_info (account, nickname, email, phone) " +
            "values (#{account}, #{nickname}, #{email}, #{phone}) ")
    int addUserInfo(UserInfo userInfo);


    @Select("select account, email, phone from user_info " +
            "where account = #{account} limit 1 ")
    UserInfo checkAccount(@Param("account") String account);

    @Select("select account, email, phone from user_info " +
            "where phone = #{phone} limit 1 ")
    UserInfo checkPhone(@Param("phone") String phone);

    @Select("select account, email, phone from user_info " +
            "where email = #{email} limit 1 ")
    UserInfo checkMail(@Param("email") String email);
}
