package com.yedean.valorant.service;

import com.yedean.valorant.pojo.PlayerInfo;

public interface UserContentService {

    //获取玩家瓦罗兰特名字信息
    PlayerInfo getPlayerInfo(String username,String accessToken,String entitleToken);
}
