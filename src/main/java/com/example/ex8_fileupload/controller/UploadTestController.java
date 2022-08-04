package com.example.ex8_fileupload.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class UploadTestController {

    @GetMapping("/uploadEx")
    public void uploadEx() {

        log.info("uploadEx");

    }

}

