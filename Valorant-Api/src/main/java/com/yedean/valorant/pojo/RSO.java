package com.yedean.valorant.pojo;

import lombok.Data;

/**
 * RSO认证端口获取的信息
 *
 * @author yedean
 * @date 2023/4/28
 */
@Data
public class RSO {

    /**
     * 用户访问token
     */
    private String accessToken;


    /**
     * 用户刷新token
     */
    private String entitlementsToken;


    /**
     * 用户唯一ID
     */
    private String userId;
}
