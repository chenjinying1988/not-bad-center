package com.jychan.notbad.domain;

import java.util.Date;

/**
 * Created by chenjinying on 2017/5/13.
 * mail: 415683089@qq.com
 */
public class UserInfo {

    private long id;
    private String account;
    private String nickname;
    private String email;
    private String phone;
    private Date ctime;
    private Date utime;

    public UserInfo() {
    }

    public UserInfo(String account, String nickname, String email, String phone) {
        this.account = account;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("id=").append(id);
        sb.append(", account='").append(account).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append('}');
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }
}
