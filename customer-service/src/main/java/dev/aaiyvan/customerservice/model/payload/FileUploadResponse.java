package dev.aaiyvan.customerservice.model.payload;

public record FileUploadResponse(
        String fileName,
        String source,
        String url
) {
}
