package dev.aaiyvan.newsservice.model.payload;

public record FileUploadResponse(
        String id,
        String source,
        String url
) {
}