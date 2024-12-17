package com.learnning.generating_jasper_reports_with_Java.service;

import com.learnning.generating_jasper_reports_with_Java.model.Aluno;
import net.sf.jasperreports.engine.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportSevice {

    public static final Logger LOGGER = LoggerFactory.getLogger(JasperReportSevice.class);

    public static final String IMAGEBACKGROUND = "classpath:jasper/img/grLoTFT.PNG";
    public static final String CERTIFICADOS = "classpath:jasper/certificados/";
    public static final String ARQUIVOJRXML = "cert.jrxml";
    public static final String DESTINOPDF =  "C:\\jasper-reports\\";

    public void generate(Aluno aluno) throws IOException {

        byte[] image = this.loadImage(IMAGEBACKGROUND);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", aluno.getNome());
        params.put("curso", aluno.getCurso());
        params.put("cargaHoraria", aluno.getCargaHoraria());
        params.put("dataInicioCurso", aluno.getDataInicioCurso());
        params.put("dataTerminoCurso", aluno.getDataFimCurso());
        params.put("imageJasper", image);

        String absolutePath = getAbsolutePath();

        try {
            String folderDestino = getDiretorioSave("certificado"+aluno.getNome());

            // codigo para compilar arquivo de definição de relatorio Jasper (geralmente com extençãp .jrml)
            JasperReport report = JasperCompileManager.compileReport(absolutePath);
            LOGGER.info("report compilado : {}", absolutePath);

            //codigo para editar ou prencher o arquivo compilado com o objecto mapeado (ou seja, com os dados do aluno)
            JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
            LOGGER.info("print compilado");

            //codigo para salvar o relatorio compilado e editado ou mapeado com os parametros para o formato desejado ex: pdf
            JasperExportManager.exportReportToPdfFile(print, folderDestino);
            LOGGER.info("certificado pdf exportado para {}", folderDestino);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadImage(String imagebackground) throws IOException {
        String img = ResourceUtils.getFile(imagebackground).getAbsolutePath();
        File file = new File(img);
        try (InputStream inputStream = new FileInputStream(file)) {
            return IOUtils.toByteArray(inputStream);
        }
    }

    private String getDiretorioSave(String name){
        this.createDiretorio(DESTINOPDF);
        return DESTINOPDF + name.concat(".pdf");
    }

    private void createDiretorio(String dirName){
        File dir = new File(dirName);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    private String getAbsolutePath() throws FileNotFoundException {
        return ResourceUtils.getFile(CERTIFICADOS+ARQUIVOJRXML).getAbsolutePath();
    }
}
