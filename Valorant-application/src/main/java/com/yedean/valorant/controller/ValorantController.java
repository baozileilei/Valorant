package com.yedean.valorant.controller;

import com.yedean.valorant.pojo.PlayerInfo;
import com.yedean.valorant.pojo.RSO;
import com.yedean.valorant.response.ResponseVo;
import com.yedean.valorant.service.ValorantService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/valorant")
public class ValorantController {

    @Resource
    ValorantService valorantService;


    @PostMapping("/login")
    public ResponseVo<?> login(@RequestParam  String username, @RequestParam String password){
        RSO rso = valorantService.login(username, password);
        if (rso ==null){
            return ResponseVo.fail(50000,"服务器错误");
        }

        return ResponseVo.ok(rso);

    }

    @PostMapping("/getPlayerInfo")
    public ResponseVo<?> getPlayerInfo(@RequestParam String username){
        PlayerInfo info = valorantService.getPlayerInfo(username);
        if (info ==null){
            return ResponseVo.fail(50000,"服务器错误");
        }

        return ResponseVo.ok(info);
    }


}
