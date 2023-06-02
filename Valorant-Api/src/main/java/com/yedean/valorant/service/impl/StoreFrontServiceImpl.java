package com.yedean.valorant.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yedean.valorant.api.InGameApiUrl;
import com.yedean.valorant.httpClient.ValorantClient;
import com.yedean.valorant.pojo.RSO;
import com.yedean.valorant.service.StoreFrontService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StoreFrontServiceImpl implements StoreFrontService {

    @Resource
    ValorantClient client;

    @Override
    public Object getStoreFront(RSO rso) {

        String accessToken = rso.getAccessToken();

        String entitlementsToken = rso.getEntitlementsToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.set("X-Riot-Entitlements-JWT",entitlementsToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        String url = InGameApiUrl.StoreFront.getUrl();

        return client.requestApi(url.replace("{userId}", rso.getUserId()), request, HttpMethod.GET);



    }
}
