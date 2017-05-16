package com.jychan.notbad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jychan.notbad.domain.UserInfo;
import com.jychan.notbad.mapper.RoleInfoMapper;
import com.jychan.notbad.service.AccountService;
import com.jychan.notbad.utils.json.JsonUtils;
import com.jychan.notbad.utils.ssdb.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjinying on 2017/5/6.
 * mail: 415683089@qq.com
 */
@Controller
public class AdminController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SESSION_ACCESS_TOKEN = "accessToken";
    private static final String SESSION_USER_INFO = "userInfo";

    @Autowired
    RoleInfoMapper roleInfoMapper;
    @Autowired
    AccountService accountService;


    @RequestMapping(value = {"/", "/admin"}, produces = {"application/json;charset=UTF-8"})
    public String admin(Model model, HttpSession session) {

        String token = (String) session.getAttribute(SESSION_ACCESS_TOKEN);
        if (token == null) {
            return "/admin/login";
        } else {
            UserInfo userInfo = (UserInfo) session.getAttribute(SESSION_USER_INFO);
            model.addAttribute("message", "什么鬼");
            model.addAttribute("username", userInfo.getNickname());
            return "/admin";
        }
    }

    /**
     * 登录验证
     */
    @RequestMapping(value="/login", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String login(Model model, HttpServletRequest request, HttpSession session, String account, String password) throws JsonProcessingException {
        // FIXME: 2017/5/13 处理异常抛出后处理
        Map<String, Object> data = new HashMap<>();

        if (account == null || "".equals(account)) {
            data.put("code", 0);
            data.put("message", "账号不能为空");
            return JsonUtils.toJson(data);
            // FIXME: 2017/5/13 封装好返回结构
        }
        if (password == null || "".equals(password)) {
            data.put("code", 0);
            data.put("message", "密码不能为空");
            return JsonUtils.toJson(data);
        }

        UserInfo userInfo = accountService.login(account, password);
        if (userInfo == null) {
            data.put("code", 0);
            data.put("message", "账号或密码有误");
            return JsonUtils.toJson(data);
        }

        logger.info("login success " + userInfo);
        String token = accountService.getToken(userInfo.getAccount());
        session.setAttribute(SESSION_ACCESS_TOKEN, token);
        session.setAttribute(SESSION_USER_INFO, userInfo);
        data.put("code", 200);
        return JsonUtils.toJson(data);
    }

    @RequestMapping(value="/register", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String register(Model model, HttpServletRequest request, HttpSession session, @RequestParam Map<String, String> param) throws JsonProcessingException {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);

        String account = param.get("account");
        String nickname = param.get("nickname");
        String email = param.get("email");
        String phone = param.get("phone");
        String password = param.get("password");

        // TODO: 2017/5/14 使用函数式编程简化代码
        if (StringUtils.isBlank(account)) {
            data.put("message", "账号不能为空");
            return JsonUtils.toJson(data);
        }
        if (StringUtils.isBlank(nickname)) {
            data.put("message", "昵称不能为空");
            return JsonUtils.toJson(data);
        }
        if (StringUtils.isBlank(password)) {
            data.put("message", "密码不能为空");
            return JsonUtils.toJson(data);
        }

        if (StringUtils.containsWhitespace(account)) {
            data.put("message", "账号不能含有空格");
            return JsonUtils.toJson(data);
        }
        if (StringUtils.containsWhitespace(nickname)) {
            data.put("message", "昵称不能含有空格");
            return JsonUtils.toJson(data);
        }
        if (StringUtils.containsWhitespace(password)) {
            data.put("message", "密码不能含有空格");
            return JsonUtils.toJson(data);
        }

        // TODO: 2017/5/14 检查邮箱格式

        if (!StringUtils.isBlank(phone) && !StringUtils.isNumeric(phone)) {
            data.put("message", "手机格式不正确 " + phone);
            return JsonUtils.toJson(data);
        }

        String passwordMd5 = Md5Utils.safeToMd5(password);
        boolean result = accountService.register(account, nickname, email, phone, passwordMd5);
        if (result) {
            data.put("code", 200);

            // TODO: 2017/5/14 添加token 及个人信息
        } else {
            data.put("code", 0);
            data.put("message", "注册失败，请稍后重试.");
        }
        return JsonUtils.toJson(data);
    }



    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello ..." + roleInfoMapper.findAll();
    }

}
