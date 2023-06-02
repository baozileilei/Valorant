package com.yedean.valorant.service.impl;

import com.yedean.valorant.pojo.RSO;
import com.yedean.valorant.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    private static final String CLIENT_KEY = "VALORANT:USER";

    @Resource
    RedisTemplate<String, Object> redisTemplate;


    @Override
    public RSO getToken(String userName) {
        return (RSO) redisTemplate.opsForHash().get(CLIENT_KEY, userName);
    }

    @Override
    public void setToken(String userName, RSO rso) {

        redisTemplate.opsForHash().put(CLIENT_KEY, userName, rso);
    }
}
