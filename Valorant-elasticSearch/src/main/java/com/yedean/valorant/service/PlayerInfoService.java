package com.yedean.valorant.service;

import com.yedean.valorant.pojo.PlayerInfo;

public interface PlayerInfoService {

    void save(PlayerInfo info);

    PlayerInfo findByUserName(String userName);

}
