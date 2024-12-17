package com.learnning.generating_jasper_reports_with_Java.model;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    private String nome;
    private String curso;
    private Integer cargaHoraria;
    private Date dataInicioCurso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Date getDataInicioCurso() {
        return dataInicioCurso;
    }

    public void setDataInicioCurso(Date dataInicioCurso) {
        this.dataInicioCurso = dataInicioCurso;
    }

    public Date getDataFimCurso() {
        return dataFimCurso;
    }

    public void setDataFimCurso(Date dataFimCurso) {
        this.dataFimCurso = dataFimCurso;
    }

    private Date dataFimCurso;
}
