package com.yedean.valorant.service;

import com.yedean.valorant.pojo.PlayerInfo;
import com.yedean.valorant.pojo.RSO;

/**
 * 瓦罗兰特相关业务类
 */
public interface ValorantService {


    RSO login(String username, String password);

    PlayerInfo getPlayerInfo(String username);

}
