package br.com.episteme.facilita.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Questao {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String texto;
    @NotBlank
    @Column(nullable = false)
    private String a1;
    @NotBlank
    @Column(nullable = false)
    private String a2;
    @NotBlank
    @Column(nullable = false)
    private String a3;
    @NotBlank
    @Column(nullable = false)
    private String a4;
    @NotBlank
    @Column(nullable = false)
    private String certa;
    @Column
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    @Column
    @Enumerated(EnumType.STRING)
    private TipoDeProva tipoDeProva;

    public Questao(String texto, String a1, String a2, String a3, String a4, String certa, Disciplina disciplina, TipoDeProva tipoDeProva) {
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
