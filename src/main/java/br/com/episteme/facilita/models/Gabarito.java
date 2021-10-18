package br.com.episteme.facilita.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
public class Gabarito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    @OneToOne
    @JoinColumn(name = "simulado_id")
    private Simulado simulado;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDateTime data;
    @OneToMany
    private List<Resposta> respostas;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Gabarito(Simulado simulado, List<Resposta> respostas, User user) {
        this.id = id;
        this.simulado = simulado;
        this.data = LocalDateTime.now();
        this.respostas = respostas;
        this.user = user;
    }

    public Gabarito(){}

    public Long getId() {
        return id;
    }

    public Simulado getSimulado() {
        return simulado;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public User getUser() {
        return user;
    }

    public Integer contarTotalAcertos(){
        int acertos = 0;
        for(Resposta r : getRespostas()){
            if(r.getAlternativa().isCerta()){
                acertos = acertos + 1;
            }
        }
        return acertos;
    }

    public int contarErros(Disciplina disciplina){
        int erros = 0;
        for(Resposta resposta : getRespostas()){
            if(resposta.getAlternativa().getQuestao().getDisciplina().equals(disciplina)){
                if(!resposta.getAlternativa().isCerta()) {
                    erros = erros + 1;
                }
            }
        }
        return erros;
    }

    public int contarAcertos(Disciplina disciplina){
        int acertos = 0;
        for(Resposta resposta : getRespostas()){
            if(resposta.getAlternativa().getQuestao().getDisciplina().equals(disciplina)){
                if(!resposta.getAlternativa().isCerta()) {
                    acertos = acertos + 1;
                }
            }
        }
        return acertos;
    }
}
