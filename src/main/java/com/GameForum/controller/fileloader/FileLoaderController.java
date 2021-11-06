package com.GameForum.controller.fileloader;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game-forum/file")
public class FileLoaderController {

    private Map<String, byte[]> files = new HashMap<>();

    @PostMapping(path = "{fileName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(
            @PathVariable(value = "fileName") String fileName,
            @RequestPart("file") MultipartFile file) throws IOException {
        files.put(fileName, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "{fileName}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity download(
            @PathVariable String fileName) {
        byte[] file = files.get(fileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + fileName + "\";charset=utf-8");

        return ResponseEntity.ok().headers(header)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .contentLength(file.length)
                .body(file);
    }

}
