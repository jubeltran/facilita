package br.com.episteme.facilita.dto;

import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.TipoDeProva;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequisicaoNovaQuestao {
    @NotBlank @Size(max = 10000)
    private String texto;
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    @Enumerated(EnumType.STRING)
    private TipoDeProva tipoDeProva;

    public RequisicaoNovaQuestao(String texto, String a1, String a2, String a3, String a4, String certa, Disciplina disciplina, TipoDeProva tipoDeProva) {
        this.texto = texto;
        this.disciplina = disciplina;
        this.tipoDeProva = tipoDeProva;
    }

    public String getTexto() {
        return texto;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public TipoDeProva getTipoDeProva() {
        return tipoDeProva;
    }
}
