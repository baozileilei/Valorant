package com.yedean.valorant.pojo;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

/**
 * 瓦罗兰特客户端版本实体类
 */
@Data
public class RiotClientVersion  {
    private String manifestId;
    private String branch;
    /**
     * 版本
     */
    private String version;

    /**
     * 开发版本
     *
     */
    private String buildVersion;

    private String engineVersion;
    private String riotClientVersion;
    private String riotClientBuild;
    private String buildDate;

}
