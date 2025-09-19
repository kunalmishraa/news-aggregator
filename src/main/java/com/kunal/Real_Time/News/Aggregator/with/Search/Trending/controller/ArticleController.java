package com.kunal.Real_Time.News.Aggregator.with.Search.Trending.controller;

import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.model.Article;
import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.scheduler.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final NewsService newsService;

//    @GetMapping("/search")
//    public List<Article> search(@RequestParam String q) {
//        return newsService.searchArticles(q);
//    }
//
//    @GetMapping("/trending")
//    public Map<String, Long> trending() {
//        return newsService.getTrendingKeywords();
//    }

    @GetMapping("/search")
    public List<Article> search(@RequestParam String q) {
        return newsService.searchArticles(q);
    }

    @GetMapping("/trending")
    public Map<String, Long> trending() {
        return newsService.getTrendingKeywords();
    }
}

