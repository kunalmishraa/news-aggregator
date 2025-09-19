package com.kunal.Real_Time.News.Aggregator.with.Search.Trending.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String title;
    private String description;
    private String url;
    private String publishedAt;
    private Source source;

    public void setCategory(String general) {
    }

    @Data
    public static class Source {
        private String id;
        private String name;
    }

}

