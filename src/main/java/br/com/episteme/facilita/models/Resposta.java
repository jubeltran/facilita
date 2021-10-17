package br.com.episteme.facilita.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    @OneToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;
    @OneToOne
    private Alternativa alternativa;

    public Resposta(Alternativa alternativa, Questao questao) {
        this.id = id;
        this.alternativa = alternativa;
        this.questao = questao;
    }

    public Resposta() {

    }

    public Long getId() {
        return id;
    }

    public Questao getQuestao() {
        return questao;
    }

    public Alternativa getAlternativa() {
        return alternativa;
    }
}
