package br.com.episteme.facilita.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Curso {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Column(nullable = false)
    private String descricao;
    @NotBlank
    @Column(nullable = false)
    private String insta;
    @NotBlank
    @Column(nullable = false)
    private String universidade1;
    @NotBlank
    @Column(nullable = false)
    private String universidade2;
    @NotBlank
    @Column(nullable = false)
    private String universidade3;
    @Column(nullable = false)
    private Double corteSisu;
    @Column(nullable = false)
    private Integer corteFuvest;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeCurso tipoDeCurso;

    public Curso(String nome, String descricao, String insta, String universidade1, String universidade2, String universidade3, Double corteSisu, Integer corteFuvest, TipoDeCurso tipoDeCurso) {
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
    public Curso(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUniversidade1() {
        return universidade1;
    }
    public void setUniversidade1(String universidade1) {
        this.universidade1 = universidade1;
    }

    public String getUniversidade2() {
        return universidade2;
    }
    public void setUniversidade2(String universidade2) {
        this.universidade2 = universidade2;
    }

    public String getUniversidade3() {
        return universidade3;
    }
    public void setUniversidade3(String universidade3) {
        this.universidade3 = universidade3;
    }

    public String getInsta() {
        return insta;
    }
    public void setInsta(String insta) {
        this.insta = insta;
    }

    public Double getCorteSisu() {
        return corteSisu;
    }
    public void setCorteSisu(double corteSisu) {
        this.corteSisu = corteSisu;
    }

    public Integer getCorteFuvest() {
        return corteFuvest;
    }
    public void setCorteFuvest(int corteFuvest) {
        this.corteFuvest = corteFuvest;
    }

    public TipoDeCurso getTipoDeCurso() {
        return tipoDeCurso;
    }
    public void setTipoDeCurso(TipoDeCurso tipoDeCurso) {
        this.tipoDeCurso = tipoDeCurso;
    }
}
