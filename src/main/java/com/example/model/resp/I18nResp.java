package com.example.model.resp;

import lombok.Data;

/**
 * @Description:
 * @Author: chenchong
 * title=User Login
 * welcome=Welcome
 * username=Username
 * password=Password
 * login=Sign In
 * @Date: 2021/11/11 16:48
 */
@Data
public class I18nResp {
    private String title;

    private String welcome;

    private String username;

    private String password;

    private String login;


}
