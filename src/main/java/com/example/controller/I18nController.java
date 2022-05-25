package com.example.controller;

import com.example.common.reponse.BaseResult;
import com.example.model.resp.I18nResp;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/11/11 16:46
 */

@RestController
@RequestMapping("/i18n")
@Api(tags = "语言国际化")
public class I18nController {
    @Autowired
    private MessageSource messageSource;

    @ApiOperation(value = "国际化测试", notes = "国际化测试")
    @GetMapping("/test")
    public BaseResult testInfo(String locale) {
        I18nResp i18nResp = new I18nResp();
        String title = messageSource.getMessage("title", null, new Locale(locale));
        String welcome = messageSource.getMessage("welcome", null, new Locale(locale));
        String username = messageSource.getMessage("username", null, new Locale(locale));
        String password = messageSource.getMessage("password", null, new Locale(locale));
        String login = messageSource.getMessage("login", null, new Locale(locale));
        i18nResp.setTitle(title);
        i18nResp.setWelcome(welcome);
        i18nResp.setUsername(username);
        i18nResp.setPassword(password);
        i18nResp.setLogin(login);
        return BaseResult.success(i18nResp);

    }

}
