package dev.aaiyvan.newsservice.client;

import dev.aaiyvan.newsservice.model.payload.FileUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "storage-service", url = "http://localhost:8012", path = "/api/v1/storage")
public interface StorageServiceClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileUploadResponse[]> upload(
            @RequestParam("source") final String source,
            @RequestParam("target") final String target,
            @RequestPart("file") final List<MultipartFile> file
    );

    @DeleteMapping("/{source}/{fileName}")
    ResponseEntity<String> delete(
            @PathVariable final String source,
            @PathVariable final String fileName
    );

}