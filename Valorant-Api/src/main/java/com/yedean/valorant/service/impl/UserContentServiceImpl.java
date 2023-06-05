package com.yedean.valorant.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yedean.valorant.api.InGameApiUrl;
import com.yedean.valorant.httpClient.ValorantClient;
import com.yedean.valorant.pojo.PlayerInfo;
import com.yedean.valorant.service.UserContentService;
import com.yedean.valorant.utils.ValorantUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserContentServiceImpl implements UserContentService {

    @Resource
    ValorantClient client;

    @Override
    public PlayerInfo getPlayerInfo(String userId,String accessToken,String entitleToken) {

        //构造请求header
        HttpHeaders header = ValorantUtils.createAuthHeader(accessToken, entitleToken);

        //构造body
        JSONArray array = new JSONArray();
        array.add(userId);

        //构造entity
        HttpEntity<String> request = new HttpEntity<>(array.toJSONString(), header);

        JSONArray response = client.requestApiForArray(InGameApiUrl.PlayerInfo.getUrl(), request, HttpMethod.PUT);

        ArrayList<PlayerInfo> playerInfos = new ArrayList<>();

        for (Object o : response) {
            PlayerInfo info = ((JSONObject) o).toJavaObject(PlayerInfo.class);
            playerInfos.add(info);
        }

        return playerInfos.get(0);
    }
}
