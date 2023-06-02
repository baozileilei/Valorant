package com.yedean.valorant.controller;

import com.yedean.valorant.service.RSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class callBackController {

    @Autowired
    RSOService rsoService;


    @RequestMapping("/oauth2")
    public String oauthCallback(@RequestParam("code") String code){
        return code;
    }

    @RequestMapping("/test/{code}")
    public Object test(@PathVariable String code){
        return rsoService.oauth2(code);
    }


}
