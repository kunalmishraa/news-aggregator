package com.kunal.Real_Time.News.Aggregator.with.Search.Trending.repository;

import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleElasticRepository extends ElasticsearchRepository<Article, String> {
    List<Article> searchByTitleOrDescription(String keyword);
}

