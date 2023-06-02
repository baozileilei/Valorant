package com.yedean.valorant.api;

/**
 * 瓦罗兰特公共开放Api与玩家信息无关的相关Api
 */
public enum ValorantComApiUrl {

    RiotClientVersion("zh-TW", "/version");


    private static final String BASE_URL = "https://valorant-api.com/v1{URI}?language={language}";

    private String url;

    ValorantComApiUrl(String language, String uri) {
        this.url = BASE_URL
                .replace("{language}", language)
                .replace("{URI}", uri);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
