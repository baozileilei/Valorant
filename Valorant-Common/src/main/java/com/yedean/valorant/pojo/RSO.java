package com.yedean.valorant.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RSO认证端口获取的信息
 *
 * @author yedean
 * @date 2023/4/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RSO {

    /**
     * 用户访问token
     */
    private String accessToken;

    /**
     * 用户刷新token
     */
    private String refreshToken;

    /**
     * 用户ID token
     */
    private String idToken;


    /**
     * 用户长期有效的信息token
     */
    private String entitlementsToken;


    /**
     * 用户唯一ID
     */
    private String userId;

//    /** 拳头客户端构建版本 */
//    private String riotClientBuild = "63.0.9.4909983.4789131";
//
//    private static final String RIOT_CLIENT_AGENT_PREFIX = "RiotClient/";
//    private static final String RIOT_CLIENT_AGENT_SUFFIX = " rso-auth (Windows;10;;Professional, x64)";
//    /** 拳头客户端代理环境 */
//    private String riotClientAgent = RIOT_CLIENT_AGENT_PREFIX + riotClientBuild + RIOT_CLIENT_AGENT_SUFFIX;
}
