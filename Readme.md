# Real-Time News Aggregator with Search and Trending System

A Spring Boot application that aggregates news articles in real-time using **NewsAPI**, stores them in **MongoDB**, indexes them in **Elasticsearch**, and uses **Redis** for trending keyword caching.

---

## Features

- **Real-Time News Search**: Search articles using keywords.
- **Trending Keywords**: View trending keywords based on article titles.
- **MongoDB Storage**: Stores fetched articles for persistence.
- **Elasticsearch Indexing**: Enables fast full-text search on articles.
- **Redis Caching**: Caches trending keywords for high-performance retrieval.
- **REST API Endpoints**:
    - `GET /articles/search?q=<keyword>` → Search articles
    - `GET /articles/trending` → Get trending keywords

---

## Tech Stack

- **Backend**: Java, Spring Boot
- **Database**: MongoDB
- **Search Engine**: Elasticsearch
- **Caching**: Redis
- **API**: NewsAPI (https://newsapi.org)
- **Build Tool**: Maven

---

## Prerequisites

- Java 21+
- Maven
- MongoDB (running locally or in Docker)
- Elasticsearch (running locally or in Docker)
- Redis (running locally or in Docker)
- NewsAPI Key ([Get API Key](https://newsapi.org/register))

---

## Installation

1. **Clone the repository**:

```bash
git clone https://github.com/kunalmishraa/news-aggregator.git
cd news-aggregator

spring.data.mongodb.uri=mongodb://localhost:27017/newsdb
spring.elasticsearch.rest.uris=http://localhost:9200
spring.redis.host=localhost
spring.redis.port=6379

newsapi.key=YOUR_NEWSAPI_KEY
newsapi.baseurl=https://newsapi.org/v2


mvn clean install


mvn spring-boot:run

GET http://localhost:8080/articles/search?q=technology

[
  {
    "title": "Latest Tech News",
    "description": "Article description...",
    "url": "https://example.com/article",
    "publishedAt": "2025-09-19T12:00:00Z",
    "source": {
      "id": "techcrunch",
      "name": "TechCrunch"
    }
  }
]


GET http://localhost:8080/articles/trending


src/main/java/com/kunal/Real_Time/News/Aggregator/with/Search/Trending
│
├── controller/       # REST controllers
├── model/            # Article & DTO classes
├── repository/       # MongoDB & Elasticsearch repositories
├── scheduler/        # Services for fetching and processing news
├── config/           # App configuration (RestTemplate, Redis, etc.)
└── Application.java  # Spring Boot main class




---

This **README.md** is ready to copy into your repo.  

It explains:

- Features  
- Tech stack  
- Installation  
- API endpoints and responses  
- Project structure  

---

If you want, I can also create a **fancier HTML version** with a **dashboard screenshot and badges for MongoDB, Elasticsearch, Redis, and Java** to make it look like a professional GitHub portfolio README.  

Do you want me to do that?

