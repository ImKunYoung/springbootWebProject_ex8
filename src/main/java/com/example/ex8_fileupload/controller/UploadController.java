package com.example.ex8_fileupload.controller;

import com.example.ex8_fileupload.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @Value("${com.example.ex8_fileupload.upload.path}") // application 의 properties 의 변수
    private String uploadPath;

    /*파일 업로드, 업로드 결과 반환*/
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile: uploadFiles) {

            // 이미지 파일만 업로드
            if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로 => 바뀐 듯 ..
            String orginalName = uploadFile.getOriginalFilename();
            assert orginalName != null;
            String fileName = orginalName.substring(orginalName.lastIndexOf("\\") + 1);

            log.info("fileName: "+fileName);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_"를 이용해서 구현
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath); // 실제 이미지 저장
                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);

    }

    /*날짜 폴더 생성*/
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder --------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if(!uploadPathFolder.exists()) {
            boolean mkdirs = uploadPathFolder.mkdirs();
            log.info("-------------------makeFolder------------------");
            log.info("uploadPathFolder.exists(): "+uploadPathFolder.exists());
            log.info("mkdirs: "+mkdirs);
        }

        return folderPath;

    }

}
