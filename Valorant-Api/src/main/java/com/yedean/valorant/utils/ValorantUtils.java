package com.yedean.valorant.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Base64;
import java.util.Collections;

/**
 * RSOIUtils
 * <p>
 * 拳头用户登录验证服务（Riot Sign On）的流程 方法库
 *
 * @author yedean
 * @date 2023/4/28
 */
public class ValorantUtils {

    //Auth URL
    public static final String AUTH_URL = "https://auth.riotgames.com/api/v1/authorization";

    //TOKEN URL
    public static final String TOKEN_URL = "https://entitlements.auth.riotgames.com/api/token/v1";

    public static final String RIOT_TOKEN_URL = "https://auth.riotgames.com/token";

    //USER URL
    public static final String USER_URL = "https://auth.riotgames.com/userinfo";


    public static HttpHeaders getHeaders() {
        //构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }


    public static HttpEntity<?> createPingEntity() {

        HttpHeaders headers = getHeaders();

        //构造请求的JSON Body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", "play-valorant-web-prod");
        jsonObject.put("nonce", "1");
        jsonObject.put("redirect_uri", "https://playvalorant.com/opt_in");
        jsonObject.put("response_type", "token id_token");

        return new HttpEntity<>(jsonObject.toJSONString(), headers);
    }


    public static HttpEntity<?> createAuthEntity(String val1, String val2) {

        HttpHeaders headers = getHeaders();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "auth");
        jsonObject.put("username", val1);
        jsonObject.put("password", val2);

        return new HttpEntity<>(jsonObject.toJSONString(), headers);
    }


    public static HttpEntity<?> createAuthEntity(String code) {

        String clientID = "yedeanTest";
        String clientSecret = "yedeanSecret";
        String text = clientID + ":" + clientSecret;

        byte[] bytes = text.getBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String auth = encoder.encodeToString(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(auth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", "https://playvalorant.com/opt_in");

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return request;
    }


    public static String parseAccessToken(JSONObject val1) {
        JSONObject val2 = val1.getJSONObject("response");
        JSONObject val3 = val2.getJSONObject("parameters");
        String val4 = val3.getString("uri");
        String[] parts = val4.replace("https://playvalorant.com/opt_in#", "").split("&");
        return parts[0].replace("access_token=", "");
    }


    public static HttpHeaders createAuthHeader(String val1,String val2){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(val1);
        headers.set("X-Riot-Entitlements-JWT",val2);

        return headers;
    }


}
