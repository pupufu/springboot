package com.ppf.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) {
        HashMap<Object, Object> map = new HashMap<>();

        try {
            file.transferTo(new File("c:11.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }


}
