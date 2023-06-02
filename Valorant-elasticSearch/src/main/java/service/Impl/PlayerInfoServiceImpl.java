package service.Impl;

import com.yedean.valorant.pojo.PlayerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import repository.PlayerInfoRepository;
import service.PlayerInfoService;
@Service
public class PlayerInfoServiceImpl implements PlayerInfoService {

    @Autowired
    PlayerInfoRepository repository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void save(PlayerInfo info) {
        repository.save(info);
    }

    @Override
    public PlayerInfo findByUserName(String userName) {
        return null;
    }

    @Override
    public void updateById(PlayerInfo info) {

    }
}
