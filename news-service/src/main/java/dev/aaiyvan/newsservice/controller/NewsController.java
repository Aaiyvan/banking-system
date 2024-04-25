package dev.aaiyvan.newsservice.controller;

import dev.aaiyvan.newsservice.model.dto.NewsResponse;
import dev.aaiyvan.newsservice.model.entity.News;
import dev.aaiyvan.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PostMapping
    public ResponseEntity<NewsResponse> createNews(
            @RequestParam final String title,
            @RequestParam final String content,
            @RequestParam("tags") final List<String> tags,
            @RequestParam("files") final List<MultipartFile> multipartFiles
    ) {
        return ResponseEntity.ok(newsService.createNews(title, content, tags, multipartFiles));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(
            @PathVariable final UUID id,
            @RequestParam final String title,
            @RequestParam final String content,
            @RequestParam("tags") final List<String> tags
    ) {
        return ResponseEntity.ok(newsService.updateNews(id, title, content, tags));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNews(
            @PathVariable final UUID id
    ) {
        return ResponseEntity.ok(newsService.getInfoNews(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(
            @PathVariable final UUID id
    ) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build(); // http code 204
    }

}