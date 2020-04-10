package com.leyou.controller;

import com.leyou.service.IUploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private IUploadService uploadService;

    @PostMapping("image")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String url = uploadService.upload(file);
        if (StringUtils.isBlank(url)) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(url);
    }
}
