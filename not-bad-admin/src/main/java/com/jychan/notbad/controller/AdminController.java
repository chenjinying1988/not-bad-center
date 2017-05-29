package com.jychan.notbad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jychan.notbad.common.Consts;
import com.jychan.notbad.domain.UserInfo;
import com.jychan.notbad.exception.ApiParaException;
import com.jychan.notbad.service.AccountService;
import com.jychan.notbad.utils.NotBadApiParaAssert;
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
public class AdminController extends ResponseErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SESSION_ACCESS_TOKEN = "accessToken";
    private static final String SESSION_USER_INFO = "userInfo";

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
    public Map<String, Object> login(Model model, HttpServletRequest request, HttpSession session, String account, String password) throws JsonProcessingException {
        Map<String, Object> data = new HashMap<>();

        NotBadApiParaAssert.isNull(account, "账号不能为空");
        NotBadApiParaAssert.isNull(password, "密码不能为空");

        // password -> MD5
        password = Md5Utils.safeToMd5(password);
        UserInfo userInfo = accountService.login(account, password);
        if (userInfo == null) {
            data.put(Consts.Common.STATUS_CODE, Consts.RESPON_CODE.SECURE_ERROR.getCode());
            data.put(Consts.Common.STATUS_MSG, Consts.RESPON_CODE.SECURE_ERROR.getPrompt());
            return data;
        }

        logger.info("login success " + userInfo.getAccount());
        String token = accountService.getToken(userInfo.getAccount());
        session.setAttribute(SESSION_ACCESS_TOKEN, token);
        session.setAttribute(SESSION_USER_INFO, userInfo);

        data.put(Consts.Common.STATUS_CODE, Consts.RESPON_CODE.SUCCESS.getCode());
        data.put(Consts.Common.STATUS_MSG, Consts.RESPON_CODE.SUCCESS.getPrompt());
        return data;
    }

    @RequestMapping(value="/register", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> register(Model model, HttpServletRequest request, HttpSession session, @RequestParam Map<String, String> param) throws JsonProcessingException {
        Map<String, Object> data = new HashMap<>();

        String account = param.get("account");
        String nickname = param.get("nickname");
        String email = param.get("email");
        String phone = param.get("phone");
        String password = param.get("password");

        NotBadApiParaAssert.isNull(account, "账号不能为空");
        NotBadApiParaAssert.isNull(nickname, "昵称不能为空");
        NotBadApiParaAssert.isNull(password, "密码不能为空");
        NotBadApiParaAssert.containsWhitespace(account, "账号不能含有空格");
        NotBadApiParaAssert.containsWhitespace(nickname, "昵称不能含有空格");
        NotBadApiParaAssert.containsWhitespace(password, "密码不能含有空格");
        NotBadApiParaAssert.notNumeric(phone, "手机格式不正确 " + phone);

        if (accountService.checkExistAccount(account)) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), "账号已被使用");
        }
        if (!StringUtils.isBlank(phone) && accountService.checkExistPhone(phone)) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), "手机已被使用");
        }
        if (!StringUtils.isBlank(email) && accountService.checkExistMail(email)) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), "邮箱已被使用");
        }

        String passwordMd5 = Md5Utils.safeToMd5(password);
        boolean result = accountService.register(account, nickname, email, phone, passwordMd5);
        if (result) {
            data.put(Consts.Common.STATUS_CODE, Consts.RESPON_CODE.SUCCESS.getCode());
            data.put(Consts.Common.STATUS_MSG, Consts.RESPON_CODE.SUCCESS.getPrompt());

            // TODO: 2017/5/14 添加token 及个人信息
        } else {
            data.put(Consts.Common.STATUS_CODE, Consts.RESPON_CODE.SUCCESS.getCode());
            data.put(Consts.Common.STATUS_MSG, "注册失败，请稍后重试.");
        }
        return data;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello ..." + accountService.getToken("test1");
    }

}
