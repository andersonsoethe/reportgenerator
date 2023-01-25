package br.com.totvs.reportgenerator.controller;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String fileUpload(@RequestParam MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (!FilenameUtils.getExtension(file.getOriginalFilename()).equals("xls")) {
            return "redirect:/upload";
        }

        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        reportService.readFile(file.getBytes());
        return "redirect:/reports";
    }
}
