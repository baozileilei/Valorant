package com.yedean.valorant.service;

import com.yedean.valorant.pojo.RSO;

public interface RedisService {

    //获取RSO凭证
    RSO getToken(String userName);

    //保存RSO凭证到数据库中
    void setToken(String userName,RSO rso);
}
