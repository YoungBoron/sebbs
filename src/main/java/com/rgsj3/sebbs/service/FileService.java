package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.FileRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;

@Service
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Resource
    FileRepository fileRepository;

    public void listFile(Model model){
        var l = fileRepository.findAll();
        model.addAttribute("fileList", l);
    }

    public Result addFile(MultipartFile multipartFile,
                          HttpServletRequest httpServletRequest) {
        LOGGER.info("123");
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return Result.error(2, "先登录");
        } else if (multipartFile.isEmpty()) {
            return Result.error(5, "空文件");
        } else {
            var date = new Date();
            String fileName = multipartFile.getOriginalFilename();
            LOGGER.info(fileName);
            String filePath = "C:/Users/Public/sebbs/";
            java.io.File dest = new java.io.File(filePath + fileName + "_" + date.getTime());
            try {
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                multipartFile.transferTo(dest);
                LOGGER.info("上传成功");
                com.rgsj3.sebbs.domain.File file = new com.rgsj3.sebbs.domain.File();
                file.setName(fileName);
                file.setPath(filePath + fileName + "_" + date.getTime());
                fileRepository.save(file);
                return Result.success();
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
            }
            return Result.error(5, "上传失败");
        }
    }
}
