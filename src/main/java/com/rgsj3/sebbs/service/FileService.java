package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

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

    public String downloadFile(HttpServletResponse response, Integer id) throws UnsupportedEncodingException {
        var file = fileRepository.findById(id).get();
        String fileName= file.getName();
        java.io.File dest = new java.io.File(file.getPath());
        if(dest.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            FileInputStream fis; //文件输入流
            BufferedInputStream bis;
            OutputStream os; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(dest);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                LOGGER.info("----------file download" + fileName);
                bis.close();
                fis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
