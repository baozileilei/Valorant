package com.yedean.valorant.service;

import com.yedean.valorant.pojo.RSO;

public interface StoreFrontService {

    /**
     * 获取商店信息
     * @param rso RSO凭证
     */
    Object getStoreFront(RSO rso);
}
