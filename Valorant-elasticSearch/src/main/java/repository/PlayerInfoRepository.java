package repository;

import com.yedean.valorant.pojo.PlayerInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlayerInfoRepository extends ElasticsearchRepository<PlayerInfo,Integer> {
}
