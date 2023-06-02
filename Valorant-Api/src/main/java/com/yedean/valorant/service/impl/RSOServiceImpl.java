package com.yedean.valorant.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yedean.valorant.httpClient.ValorantClient;
import com.yedean.valorant.pojo.RSO;
import com.yedean.valorant.service.RSOService;
import com.yedean.valorant.utils.ValorantUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RSOServiceImpl implements RSOService {

    @Resource
    ValorantClient client;


    @Override
    public RSO auth(String username, String password) {

        String accessToken = null;

        boolean skipAuth = false;

        //利用RSO工具类获取请求类
        HttpEntity<?> pingEntity = ValorantUtils.createPingEntity();
        //第一步 ping Riot认证url，记住当前设备
        JSONObject pingResponse = client.requestApi(ValorantUtils.AUTH_URL, pingEntity, HttpMethod.POST);
        if ("auth".equals(pingResponse.getString("type"))) {
            //TODO ping失败 可恶
        }
        //直接解析
        if ("response".equals(pingResponse.getString("type"))){
            accessToken = ValorantUtils.parseAccessToken(pingResponse);
            skipAuth=true;
        }

        //第二步，开始校验 用户与密码 获取accessToken
        if (!skipAuth){
            HttpEntity<?> authEntity = ValorantUtils.createAuthEntity(username, password);
            JSONObject authResponse = client.requestApi(ValorantUtils.AUTH_URL, authEntity, HttpMethod.PUT);
            accessToken = ValorantUtils.parseAccessToken(authResponse);
        }

        //第三步 TODO 邮箱验证 目前跳过邮箱验证

        //第四步 获取entitlements_token
        HttpHeaders headers = ValorantUtils.getHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        JSONObject tokenResponse = client.requestApi(ValorantUtils.TOKEN_URL, entity, HttpMethod.POST);
        String entitlementsToken = tokenResponse.getString("entitlements_token");


        //第五步 获取userId
        JSONObject userResponse = client.requestApi(ValorantUtils.USER_URL, entity, HttpMethod.GET);
        String userId = userResponse.getString("sub");


        //封装结果RSO
        return RSO.builder()
                .accessToken(accessToken)
                .userId(userId)
                .entitlementsToken(entitlementsToken)
                .build();
    }

    @Override
    public RSO oauth2(String code) {
        //构造请求体
        HttpEntity<?> request = ValorantUtils.createAuthEntity(code);

        //根据code换取token信息
        JSONObject response = client.requestApi(ValorantUtils.RIOT_TOKEN_URL, request, HttpMethod.POST);

        //请求token
        String accessToken = response.get("access_token").toString();

        //刷新token
        String refreshToken = response.get("refresh_token").toString();

        //ID Token
        String idToken = response.get("id_token").toString();

        //根据accessToken获取entitlements_token
        HttpHeaders headers = ValorantUtils.getHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        JSONObject tokenResponse = client.requestApi(ValorantUtils.TOKEN_URL, entity, HttpMethod.POST);
        String entitlementsToken = tokenResponse.getString("entitlements_token");

        //第五步 获取userId
        JSONObject userResponse = client.requestApi(ValorantUtils.USER_URL, entity, HttpMethod.GET);
        String userId = userResponse.getString("sub");

        return RSO.builder().accessToken(accessToken).refreshToken(refreshToken).entitlementsToken(entitlementsToken).idToken(idToken).userId(userId).build();
    }
}
