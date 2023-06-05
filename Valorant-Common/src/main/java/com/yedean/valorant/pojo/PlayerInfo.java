package com.yedean.valorant.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家信息
 */
@Data
@Document(indexName = "valorant_player_info",shards = 1,replicas = 1)
public class PlayerInfo implements Serializable {
    @Field(name = "display_name",type = FieldType.Keyword)
    private String displayName;
    @Field(name = "subject",type = FieldType.Keyword)
    private String subject;
    @Field(name = "game_name",type = FieldType.Keyword)
    private String gameName;
    @Field(name = "tag_line",type = FieldType.Keyword)
    private String tagLine;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Field(type= FieldType.Date, format= DateFormat.custom, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Field(type= FieldType.Date, format= DateFormat.custom, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateTime;
}
