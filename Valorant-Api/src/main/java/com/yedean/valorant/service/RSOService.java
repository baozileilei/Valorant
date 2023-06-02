package com.yedean.valorant.service;

import com.yedean.valorant.pojo.RSO;

public interface RSOService {


    /**
     *  完整的拳头登录验证流程
     *
     */
    RSO auth(String username,String password);


    /**
     * 拳头RSO oauth2鉴权
     *
     * @param code AccessToken
     * @return RSO实体类
     */
    RSO oauth2(String code);
}
