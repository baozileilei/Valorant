package com.yedean.valorant.api;

/**
 * 瓦罗兰特游戏内Api
 */
public enum InGameApiUrl {

    //获取商店内容信息
    StoreFront("pd", "ap", "/store/v2/storefront/{userId}"),

    //获取游戏用户信息
    PlayerInfo("pd","ap","/name-service/v2/players");


    private final String url;

    private static final String BASE_URL = "https://{endPoint}.{server}.a.pvp.net";

    public String getUrl() {
        return url;
    }

    InGameApiUrl(String endPoint, String server, String URI) {
        this.url = BASE_URL
                .replace("{endPoint}", endPoint)
                .replace("{server}", server)
                + URI;
    }

}
