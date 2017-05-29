package com.jychan.notbad.controller;

import com.jychan.notbad.common.Consts;
import com.jychan.notbad.exception.ApiParaException;
import com.jychan.notbad.exception.SecurityException;
import com.jychan.notbad.utils.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
public class ResponseErrorHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 请求数据的 API异常捕捉，以ResponseBody 返回
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ApiParaException.class)
    @ResponseBody
    public Map<String, Object> handlerApiParaException(HttpServletRequest request, ApiParaException e) {
        logger.debug("request '{}' found api parameter error.", request.getRequestURI(), e);
        Map<String, Object> model = new HashMap<>();
        model.put(Consts.Common.STATUS_CODE, e.getStatusCode());
        model.put(Consts.Common.STATUS_MSG, e.getStatusMsg());
        return model;
    }


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
