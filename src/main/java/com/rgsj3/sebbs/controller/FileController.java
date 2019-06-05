package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.FileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
public class FileController {

    @Resource
    FileService fileService;

    @RequestMapping("/add/file")
    public Result addFile(@RequestParam("file") MultipartFile multipartFile,
                          HttpServletRequest httpServletRequest){
        return fileService.addFile(multipartFile, httpServletRequest);
    }

    @RequestMapping("/download/{id}")
    public String downLoadFile(HttpServletResponse response,
                           @PathVariable("id") Integer id) throws UnsupportedEncodingException {
        return fileService.downloadFile(response, id);
    }

    @RequestMapping("/add/homework")
    public Result addHomework(@RequestParam("id") Integer id,
                              @RequestParam("title") String title,
                              @RequestParam("deadDate") String date,
                              HttpServletRequest httpServletRequest) {
        return fileService.addHomework(id, title, date, httpServletRequest);
    }

    @RequestMapping("/add/taskOrAssignment")
    public Result addTaskOrAssignment(@RequestParam("file") MultipartFile multipartFile,
                          @RequestParam("id") Integer id,
                          @RequestParam("type") String type,
                          HttpServletRequest httpServletRequest){
        return fileService.addFile(multipartFile, id, type, httpServletRequest);
    }
}
