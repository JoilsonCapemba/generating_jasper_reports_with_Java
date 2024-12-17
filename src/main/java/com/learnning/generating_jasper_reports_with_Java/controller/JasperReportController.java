package com.learnning.generating_jasper_reports_with_Java.controller;

import com.learnning.generating_jasper_reports_with_Java.model.Aluno;
import com.learnning.generating_jasper_reports_with_Java.service.JasperReportSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("jasper-report")
public class JasperReportController {

    @Autowired
     JasperReportSevice jasperReportSevice;

    @PostMapping("/certificado")
    public void generateJasperReport(@RequestBody Aluno aluno) throws IOException {
        this.jasperReportSevice.generate(aluno);
    }
}
