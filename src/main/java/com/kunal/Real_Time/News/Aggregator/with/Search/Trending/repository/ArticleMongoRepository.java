package com.kunal.Real_Time.News.Aggregator.with.Search.Trending.repository;

import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleMongoRepository extends MongoRepository<Article, String> {}


