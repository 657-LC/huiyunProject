package com.gec.mall.search.mapper;
import com.gec.mall.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs,String> {


}
