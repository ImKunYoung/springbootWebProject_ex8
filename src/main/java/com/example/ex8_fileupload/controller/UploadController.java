package com.example.ex8_fileupload.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@Log4j2
public class UploadController {

    @Value("${com.example.ex8_fileupload.upload.path}") // application 의 properties 의 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles) {

        for (MultipartFile uploadFile: uploadFiles) {

            // 이미지 파일만 업로드
            if (!uploadFile.getContentType().startsWith("image")) {
                log.warn("this file is not image type");
                return;
            }

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String orginalName = uploadFile.getOriginalFilename();
            assert orginalName != null;
            String fileName = orginalName.substring(orginalName.lastIndexOf("\\") + 1);

            log.info("fileName: "+fileName);

            // 날짜 폴더 생성
            String folderPath = makeFolder();



        }

    }

    /*날짜 폴더 생성*/
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder --------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;

    }

}
