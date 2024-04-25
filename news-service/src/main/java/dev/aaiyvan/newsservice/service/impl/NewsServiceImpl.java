package dev.aaiyvan.newsservice.service.impl;

import dev.aaiyvan.newsservice.client.StorageServiceClient;
import dev.aaiyvan.newsservice.exception.NewsNotFoundException;
import dev.aaiyvan.newsservice.mapper.NewsMapper;
import dev.aaiyvan.newsservice.model.dto.NewsResponse;
import dev.aaiyvan.newsservice.model.entity.FileAttachment;
import dev.aaiyvan.newsservice.model.entity.News;
import dev.aaiyvan.newsservice.model.payload.FileUploadResponse;
import dev.aaiyvan.newsservice.repository.FileAttachmentRepository;
import dev.aaiyvan.newsservice.repository.NewsRepository;
import dev.aaiyvan.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;
    private final NewsRepository newsRepository;
    private final StorageServiceClient storageServiceClient;
    private final FileAttachmentRepository fileAttachmentRepository;

    @Value("${minio.bucket.content-bucket}")
    private String NEWS_CONTENT_SOURCE;

    @Override
    public NewsResponse createNews(
            final String title,
            final String content,
            final List<String> tags,
            final List<MultipartFile> multipartFiles
    ) {
        News news = News.builder()
                .title(title)
                .content(content)
                .tags(tags)
                .build();

        News savedNews = newsRepository.save(news);
        FileUploadResponse[] responses = storageServiceClient.upload(NEWS_CONTENT_SOURCE, String.valueOf(savedNews.getId()), multipartFiles).getBody();
        Set<FileAttachment> fileAttachments = Arrays.stream(Objects.requireNonNull(responses))
                .map(r -> new FileAttachment(r.id(), r.source(), r.url(), savedNews))
                .map(fileAttachmentRepository::save)
                .collect(Collectors.toSet());

        savedNews.setFiles(fileAttachments);

        newsRepository.saveAndFlush(savedNews);
        log.info("Saving news with id {}", savedNews.getId());

        return newsMapper.toResponse(savedNews);
    }

    @Override
    public NewsResponse updateNews(
            final UUID id,
            final String title,
            final String content,
            final List<String> tags
    ) {
        News news = getById(id);
        news.setTitle(title);
        news.setContent(content);
        news.setTags(tags);

        newsRepository.save(news);
        log.info("Updating news with id {}", news.getId());

        return newsMapper.toResponse(news);
    }

    @Override
    public List<NewsResponse> getAllNews() {
        return newsMapper.toResponse(newsRepository.findAll());
    }

    @Override
    public NewsResponse getInfoNews(
            final UUID id
    ) {
        return newsMapper.toResponse(getById(id));
    }

    @Override
    public News getById(
            final UUID id
    ) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
    }

    @Override
    public void deleteById(
            final UUID id
    ) {
        newsRepository.delete(getById(id));
    }
    
}
