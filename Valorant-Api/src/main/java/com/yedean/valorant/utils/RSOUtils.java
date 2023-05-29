package com.yedean.valorant.utils;

import com.yedean.valorant.pojo.RSO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * RSOIUtils
 *
 * 拳头用户登录验证服务（Riot Sign On）的流程 方法库
 * @author yedean
 * @date 2023/4/28
 * 
 */
@Slf4j
public class RSOUtils {
    
    
    private static final Map<String,String> HEADER= new HashMap<>();
    
    
    private static final Map<String,String> RSO_BODY = new HashMap<>();
    
    
    private static void initHeader(){
        HEADER.put("Cache-Control", "no-cache");
        HEADER.put("Accept", "application/json");
        HEADER.put("Content-Type", "application/json");
    }
    
    
    private static void initBody(){
//        RSO_BODY.put("acr_values", "");
//        RSO_BODY.put("claims", "");
//        RSO_BODY.put("client_id", "riot-client");
//        RSO_BODY.put("code_challenge", "");
//        RSO_BODY.put("code_challenge_method", "");
//        RSO_BODY.put("nonce", "S357dE8QSuE"); // python token_urlsafe(16)
//        RSO_BODY.put("redirect_uri", "http://localhost/redirect");
//        RSO_BODY.put("response_type", "token id_token");
//        RSO_BODY.put("scope", "openid link ban lol_region account");

        RSO_BODY.put("client_id", "play-valorant-web-prod");
        RSO_BODY.put("nonce", "1");
        RSO_BODY.put("redirect_uri", "https://playvalorant.com/opt_in");
        RSO_BODY.put("response_type", "token id_token");
    }
    
    
    public static Map<String,String> getHeader(){
        if (HEADER.isEmpty()){
            initHeader();
        }
        
        return HEADER;
    }
    
    
    public static Map<String,String> getRsoBody(){
        if (RSO_BODY.isEmpty()){
            initBody();
        }
        return RSO_BODY;
    }

    
}
