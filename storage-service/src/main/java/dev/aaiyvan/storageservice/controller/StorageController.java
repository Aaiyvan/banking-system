package dev.aaiyvan.storageservice.controller;

import dev.aaiyvan.storageservice.model.dto.FileUploadResponse;
import dev.aaiyvan.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/storage")
public class StorageController {

    private final StorageService storageService;

    // source - bucket | target - id | file
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponse[]> upload(
            @RequestParam("source") final String source,
            @RequestParam("target") final String target,
            @RequestPart("file") final List<MultipartFile> file
    ) {
        return ResponseEntity.ok(
                storageService.uploadFile(source, target, file)
        );
    }

    @GetMapping("/{source}/{fileName}")
    public ResponseEntity<?> download(
            @PathVariable final String source,
            @PathVariable final String fileName
    ) {
        var content = storageService.downloadFile(source, fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(content));
    }

    @DeleteMapping("/{source}/{fileName}")
    public ResponseEntity<String> delete(
            @PathVariable final String source,
            @PathVariable final String fileName
    ) {
        storageService.deleteFile(source, fileName);
        return ResponseEntity.ok("%s removed successfully".formatted(fileName));
    }

    @GetMapping("/info")
    public ResponseEntity<String[]> info(
            @RequestParam final String url
    ) {
        return ResponseEntity.ok(storageService.extractSourceAndFileName(url));
    }

}
