package com.jychan.notbad.controller;

import com.jychan.notbad.common.Consts;
import com.jychan.notbad.domain.UserInfo;
import com.jychan.notbad.exception.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends ResponseErrorHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("")
    public ModelAndView list() throws Exception {
        ModelAndView view = new ModelAndView("permission/list");
		/*
		 * List<Permission> allPermission =
		 * permissionService.findAllPermission();
		 * view.addObject("permissionList", allPermission);
		 */
        return view;
    }

    @RequestMapping(value="/queryUserPermission.json", produces = {"application/json;charset=UTF-8"})
    public ModelAndView queryPermission(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Consts.LOGIN_USER);
        if (userInfo == null) {
            throw new SecurityException(Consts.RESPON_CODE.NOT_LOGIN_ERROR.getCode(), "用户未登录");
        }

        String userMenus = "hello permissions..";
        ModelAndView view = new ModelAndView();
        view.addObject(Consts.Common.STATUS_CODE, Consts.SC.SUCCESS);
        view.addObject("permissions", userMenus);
        return view;
    }

    @RequestMapping(value="/queryUserPermission.json2", produces = {"application/json;charset=UTF-8"})
    public ModelAndView queryPermission2(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Consts.LOGIN_USER);
        if (userInfo == null) {
            throw new RuntimeException("测试异常");
        }

        String userMenus = "hello permissions..";
        ModelAndView view = new ModelAndView();
        view.addObject(Consts.Common.STATUS_CODE, Consts.SC.SUCCESS);
        view.addObject("permissions", userMenus);
        return view;
    }

    private Collection<Object> getUserPermissions() {
        return null;
    }
}
