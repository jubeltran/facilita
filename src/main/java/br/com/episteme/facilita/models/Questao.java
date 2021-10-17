package br.com.episteme.facilita.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Questao {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false) @NotBlank @Size(max = 10000)
    private String texto;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    @Column
    @Enumerated(EnumType.STRING)
    private TipoDeProva tipoDeProva;
    @OneToMany(mappedBy = "questao", fetch = FetchType.EAGER)
    private List<Alternativa> alternativas;

    public Questao(String texto, Disciplina disciplina, TipoDeProva tipoDeProva) {
        this.texto = texto;
        this.disciplina = disciplina;
        this.tipoDeProva = tipoDeProva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setTipoDeProva(TipoDeProva tipoDeProva) {
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

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
}

