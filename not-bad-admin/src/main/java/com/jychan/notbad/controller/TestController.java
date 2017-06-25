package com.jychan.notbad.controller;

import com.jychan.notbad.common.Consts;
import com.jychan.notbad.exception.ApiParaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjinying on 2017/6/3.
 * mail: 415683089@qq.com
 */
@Controller
@RequestMapping("/test")
public class TestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/upload")
    @ResponseBody
    public String hello() {
        return "hello ...";
    }

}
