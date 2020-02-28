//package com.catdog.springboot.web;
//
//import com.catdog.springboot.service.FileUploadService;
//import com.catdog.springboot.web.dto.FileUploadResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//@RequiredArgsConstructor
//@RestController
//public class FileUploadController {
//
//    private final FileUploadService uploadService;
//
//    @PostMapping("/auth/user/profileimg/{email}")
//    public String uploadFile(@PathVariable String email, @RequestParam("file") MultipartFile file) {
//        String fileName = uploadService.storeFile(file, email);
//        System.out.println(fileName);
//        return fileName;
//    }
//
//    @PostMapping("/auth/posts/postimg")
//    public FileUploadResponseDto uploadFile(@RequestParam("file") MultipartFile file, String email) {
//        System.out.println(file);
//
//        String fileName = uploadService.storeFile(file, email);
//        System.out.println(fileName);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//
//        return new FileUploadResponseDto(fileName, file.getContentType(), file.getSize());
//    }
//
//
//}