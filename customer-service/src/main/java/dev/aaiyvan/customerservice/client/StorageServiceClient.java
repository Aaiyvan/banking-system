package dev.aaiyvan.customerservice.client;

import dev.aaiyvan.customerservice.model.payload.FileUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "storage-service",  url = "http://localhost:8012",  path = "/api/v1/storage")
public interface StorageServiceClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileUploadResponse[]> uploadAvatar(
            @RequestParam("source") String source,
            @RequestParam("target") String target,
            @RequestPart("file") MultipartFile file
    );

    @DeleteMapping("/{source}/{fileName}")
    ResponseEntity<String> deleteAvatar(
            @PathVariable String source,
            @PathVariable String fileName
    );

    @GetMapping("/info")
    ResponseEntity<String[]> getInfoAvatar(
            @RequestParam String url
    );

}
