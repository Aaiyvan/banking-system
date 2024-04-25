package dev.aaiyvan.newsservice.mapper;

import dev.aaiyvan.newsservice.model.dto.NewsResponse;
import dev.aaiyvan.newsservice.model.entity.News;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsResponse toResponse(News news);

    List<NewsResponse> toResponse(List<News> news);

}
