package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class FileController {

    @Resource
    FileService fileService;

    @RequestMapping("/add/file")
    public String addFile(@RequestParam("file") MultipartFile multipartFile,
                          HttpServletRequest httpServletRequest){
        Result result = fileService.addFile(multipartFile, httpServletRequest);
        if (result.getCode() == 0)
            return "download";
        else
            return "home";
    }
}
