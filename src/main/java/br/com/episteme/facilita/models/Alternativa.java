package br.com.episteme.facilita.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Alternativa {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private boolean certa;
    @NotBlank @Size(max = 10000)
    private String texto;
    @ManyToOne
    @JoinColumn(name="questao_id", nullable = false)
    private Questao questao;

    public Alternativa(){

    }

    public Alternativa(boolean certa, String texto, Questao questao) {
        this.certa = certa;
        this.texto = texto;
        this.questao = questao;
    }

    public boolean isCerta() {
        return certa;
    }

    public void setCerta(boolean certa) {
        this.certa = certa;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
}
