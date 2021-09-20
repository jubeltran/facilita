package br.com.episteme.facilita.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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
    @Column(nullable = false) @NotBlank @Size(max = 10000)
    private String texto;
    @Column
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    @Column
    @Enumerated(EnumType.STRING)
    private TipoDeProva tipoDeProva;
    //@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    //@JoinColumn(name="id")
    //private Alternativa alternativa;

    public Questao(String texto, Disciplina disciplina, TipoDeProva tipoDeProva) {
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
