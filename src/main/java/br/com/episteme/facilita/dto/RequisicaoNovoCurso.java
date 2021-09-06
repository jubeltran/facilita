package br.com.episteme.facilita.dto;

import br.com.episteme.facilita.models.TipoDeCurso;
import br.com.episteme.facilita.models.TipoDeProva;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class RequisicaoNovoCurso {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private String insta;
    @NotBlank
    private String universidade1;
    @NotBlank
    private String universidade2;
    @NotBlank
    private String universidade3;
    @NotNull
    private Double corteSisu;
    @NotNull
    private Integer corteFuvest;
    @Enumerated(EnumType.STRING)
    private TipoDeCurso tipoDeCurso;

    public RequisicaoNovoCurso(String nome, String descricao, String insta, String universidade1, String universidade2, String universidade3, Double corteSisu, Integer corteFuvest, TipoDeCurso tipoDeCurso){
        this.nome = nome;
        this.descricao = descricao;
        this.insta = insta;
        this.universidade1 = universidade1;
        this.universidade2 = universidade2;
        this.universidade3 = universidade3;
        this.corteSisu = corteSisu;
        this.corteFuvest = corteFuvest;
        this.tipoDeCurso = tipoDeCurso;
    }
    public String getUniversidade1() {
        return universidade1;
    }

    public String getUniversidade2() {
        return universidade2;
    }

    public String getUniversidade3() {
        return universidade3;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getInsta() {
        return insta;
    }

    public Double getCorteSisu() {
        return corteSisu;
    }

    public Integer getCorteFuvest() {
        return corteFuvest;
    }

    public TipoDeCurso getTipoDeCurso(){ return tipoDeCurso; }
}