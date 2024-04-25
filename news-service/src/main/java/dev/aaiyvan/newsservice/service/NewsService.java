package dev.aaiyvan.newsservice.service;

import dev.aaiyvan.newsservice.model.dto.NewsResponse;
import dev.aaiyvan.newsservice.model.entity.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface NewsService {

    NewsResponse createNews(String title, String content, List<String> tags, List<MultipartFile> multipartFiles);

    NewsResponse updateNews(UUID id, String title, String content, List<String> tags);

    List<NewsResponse> getAllNews();

    NewsResponse getInfoNews(UUID id);

    News getById(UUID id);

    void deleteById(UUID id);

}