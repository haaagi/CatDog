package com.catdog.springboot.web;

import com.catdog.springboot.service.FileUploadDownloadService;
import com.catdog.springboot.web.dto.FileUploadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadDownloadService service;

    @PostMapping("/uploadFile")
    public FileUploadResponseDto uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file);

        String fileName = service.storeFile(file);
        System.out.println(fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new FileUploadResponseDto(fileName, file.getContentType(), file.getSize());
    }
}