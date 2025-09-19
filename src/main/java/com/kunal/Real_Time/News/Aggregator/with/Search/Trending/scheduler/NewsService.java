package com.kunal.Real_Time.News.Aggregator.with.Search.Trending.scheduler;

import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.model.Article;
import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.repository.ArticleElasticRepository;
import com.kunal.Real_Time.News.Aggregator.with.Search.Trending.repository.ArticleMongoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final ArticleMongoRepository mongoRepo;
    private final ArticleElasticRepository elasticRepo;
    private final RedisTemplate<String, String> redisTemplate;

    private final String API_KEY = "99f2cca59cd7419da15f6d2c62e2cbf0";
    private final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${newsapi.key}")
    private String apiKey;

    @Value("${newsapi.baseurl}")
    private String baseUrl;



    @Scheduled(fixedRate = 60000) // every 1 min
    public void fetchNews() {
        List<Article> articles = callNewsApi();
        if (!articles.isEmpty()) {
            mongoRepo.saveAll(articles);
            elasticRepo.saveAll(articles);
        }
    }

    public List<Article> callNewsApi() {
        ResponseEntity<Map> response = restTemplate.getForEntity(NEWS_API_URL, Map.class);

        List<Article> articles = new ArrayList<>();

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            List<Map<String, Object>> newsList = (List<Map<String, Object>>) response.getBody().get("articles");

            for (Map<String, Object> news : newsList) {
                Article article = new Article();
                article.setTitle((String) news.get("title"));
                article.setDescription((String) news.get("description"));
                article.setUrl((String) news.get("url"));
                article.setPublishedAt((String) news.get("publishedAt"));

                // Source comes as nested object {id:..., name:...}
                Map<String, Object> source = (Map<String, Object>) news.get("source");
                if (source != null) {
                    article.setSource((Article.Source) source.get("name"));
                }

                article.setCategory("general"); // You can categorize based on keyword logic
                articles.add(article);
            }
        }
        return articles;
    }


    // Fetch articles by search query
    public List<Article> searchArticles(String query) {
        String url = baseUrl + "/everything?q=" + query + "&apiKey=" + apiKey;
        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);

        if (response != null && response.getArticles() != null) {
            return response.getArticles();
        }

        return Collections.emptyList();
    }

    // Fetch trending keywords from top headlines
    public Map<String, Long> getTrendingKeywords() {
        String url = baseUrl + "/top-headlines?country=us&apiKey=" + apiKey;
        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);

        if (response != null && response.getArticles() != null) {
            return response.getArticles().stream()
                    .flatMap(article -> Arrays.stream(Optional.ofNullable(article.getTitle()).orElse("").split(" ")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .filter(word -> word.length() > 3) // ignore very short words
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        }

        return Collections.emptyMap();
    }

    // DTO class to map the JSON response from NewsAPI
    @Data
    private static class NewsApiResponse {
        private String status;
        private int totalResults;
        private List<Article> articles;
    }
}

