package com.yedean.valorant.service.Impl;

import com.yedean.valorant.pojo.PlayerInfo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.yedean.valorant.service.PlayerInfoService;

import java.util.Date;
import java.util.List;

@Service
@Component
public class PlayerInfoServiceImpl implements PlayerInfoService {


    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void save(PlayerInfo info) {
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        elasticsearchRestTemplate.save(info);
    }

    @Override
    public PlayerInfo findByUserName(String userName) {
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder  = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("username",userName));
        query.withQuery(boolQueryBuilder);
        SearchHits<PlayerInfo> searchHits = elasticsearchRestTemplate.search(query.build(), PlayerInfo.class);
        List<SearchHit<PlayerInfo>> hits = searchHits.getSearchHits();
        if (hits.isEmpty()){
            return null;
        }
        return hits.get(0).getContent();
    }

}
