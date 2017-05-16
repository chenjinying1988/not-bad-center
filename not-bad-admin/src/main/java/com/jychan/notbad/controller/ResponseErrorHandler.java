package com.jychan.notbad.controller;

import com.jychan.notbad.common.Consts;
import com.jychan.notbad.exception.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
public class ResponseErrorHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(SecurityException.class)
    public ModelAndView handlerSecurityException(HttpServletRequest request, SecurityException e) {
        logger.error("request '{}' found security error.", request.getRequestURI(), e);
        ModelAndView mav = new ModelAndView();
        mav.addObject(Consts.Common.STATUS_CODE, e.getStatusCode());
        mav.addObject(Consts.Common.STATUS_MSG, e.getStatusMsg());
        mav.setViewName("auth-fail");
        mav.addObject("err_msg", e.getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, SecurityException e) {
        logger.error("request '{}' found security error.", request.getRequestURI(), e);
        ModelAndView mav = new ModelAndView();
        mav.addObject(Consts.Common.STATUS_CODE, e.getStatusCode());
        mav.addObject(Consts.Common.STATUS_MSG, e.getStatusMsg());
        mav.setViewName("auth-fail");
        mav.addObject("err_msg", e.getMessage());
        return mav;
    }


}
