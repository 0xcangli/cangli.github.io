package cn.cangli.controller;

import cn.cangli.pojo.Result;
import cn.cangli.utils.AliyunOssOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequestMapping("/upload")
@RestController
public class UploadController {

    @Autowired
    AliyunOssOperator aliyunOssOperator;

    @PostMapping
    public Result handleFileUpload(MultipartFile file) throws Exception {
        log.info("文件上传:{} {} {}", file);
//        System.out.println(file);
        //获取原始文件名
//        String originalFilename = file.getOriginalFilename();
        //保存文件
//        file.transferTo(new File("/home/ime/Desktop/img/"+originalFilename));
        String url = aliyunOssOperator.upload(file.getBytes(),file.getOriginalFilename());
        return Result.success(url);
    }
}
