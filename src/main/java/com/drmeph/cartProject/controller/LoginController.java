package com.drmeph.cartProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kdorfer on 2016-11-05.
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login() {
        return "success";
    }
}
