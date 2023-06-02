package com.yedean.valorant.httpClient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class ValorantClient {
    @Resource
    RestTemplate restTemplate;

    public JSONObject requestApi(String url,HttpEntity<?> entity,HttpMethod method){
        ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);

        return JSONObject.parseObject(response.getBody());
    }

    public JSONArray requestApiForArray(String url,HttpEntity<?> entity,HttpMethod method){
        ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
        return JSONArray.parseArray(response.getBody());
    }

}
