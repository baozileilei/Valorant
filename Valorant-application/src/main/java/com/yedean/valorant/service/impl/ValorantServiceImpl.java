package com.yedean.valorant.service.impl;

import com.yedean.valorant.pojo.PlayerInfo;
import com.yedean.valorant.pojo.RSO;
import com.yedean.valorant.service.RSOService;
import com.yedean.valorant.service.RedisService;
import com.yedean.valorant.service.UserContentService;
import com.yedean.valorant.service.ValorantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ValorantServiceImpl implements ValorantService {

    @Resource
    RSOService rsoService;
    @Resource
    RedisService redisService;
    @Resource
    UserContentService userContentService;

    @Override
    public RSO login(String username, String password) {
        RSO auth = rsoService.auth(username, password);

        if (Objects.isNull(auth)) {
            //保存到Redis缓存
            return null;
        }
        //保存到Redis缓存
        redisService.setToken(username, auth);

        return auth;
    }


    @Override
    public PlayerInfo getPlayerInfo(String username) {
        RSO rso = redisService.getToken(username);

        String userId = rso.getUserId();
        String accessToken = rso.getAccessToken();
        String entitlementsToken = rso.getEntitlementsToken();

        return userContentService.getPlayerInfo(userId,accessToken,entitlementsToken);
    }
}
