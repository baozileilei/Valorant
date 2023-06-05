package com.yedean.valorant.service.impl;

import com.yedean.valorant.pojo.PlayerInfo;
import com.yedean.valorant.pojo.RSO;
import com.yedean.valorant.service.RSOService;
import com.yedean.valorant.service.RedisService;
import com.yedean.valorant.service.UserContentService;
import com.yedean.valorant.service.ValorantService;
import org.springframework.stereotype.Service;
import com.yedean.valorant.service.PlayerInfoService;

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
    @Resource
    PlayerInfoService playerInfoService;

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

        //先从Es那边拿取信息
        PlayerInfo info = playerInfoService.findByUserName(username);
        if (info != null) {
            return info;
        }

        RSO rso = redisService.getToken(username);

        String userId = rso.getUserId();
        String accessToken = rso.getAccessToken();
        String entitlementsToken = rso.getEntitlementsToken();

        PlayerInfo playerInfo = userContentService.getPlayerInfo(userId, accessToken, entitlementsToken);
        playerInfoService.save(playerInfo);

        return playerInfo;
    }
}
