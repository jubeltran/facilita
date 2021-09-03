package br.com.episteme.facilita.dto;

import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.TipoDeProva;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequisicaoNovaQuestao {
    @NotBlank
    private String texto;
    @NotBlank
    private String a1;
    @NotBlank
    private String a2;
    @NotBlank
    private String a3;
    @NotBlank
    private String a4;
    @NotBlank
    private String certa;
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    @Enumerated(EnumType.STRING)
    private TipoDeProva tipoDeProva;

    public RequisicaoNovaQuestao(String texto, String a1, String a2, String a3, String a4, String certa, Disciplina disciplina, TipoDeProva tipoDeProva) {
        this.texto = texto;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.certa = certa;
        this.disciplina = disciplina;
        this.tipoDeProva = tipoDeProva;
    }

    public String getTexto() {
        return texto;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public String getCerta() {
        return certa;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public TipoDeProva getTipoDeProva() {
        return tipoDeProva;
    }
}
