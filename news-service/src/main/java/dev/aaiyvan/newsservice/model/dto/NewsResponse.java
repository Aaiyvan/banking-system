package dev.aaiyvan.newsservice.model.dto;

import java.util.List;

public record NewsResponse(
        String title,
        String content,
        List<String> tags
) {
}
