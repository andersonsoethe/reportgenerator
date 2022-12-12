package br.com.totvs.reportgenerator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.totvs.reportgenerator.service.ReportService;

@Controller
public class UploadController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam MultipartFile file){
        try{
            reportService.readFile(file.getBytes());
        }catch (Exception e){
            System.out.println(e);
        }
        return "upload";
    }

}
