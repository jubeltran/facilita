package br.com.episteme.facilita.models;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Simulado {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Questao> questoes;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = true)
    private User user;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime data;

    public Simulado(Long id, List<Questao> questoes){
        this.id = id;
        this.questoes = questoes;
        this.data = LocalDateTime.now();
    }

    public Simulado(List<Questao> questoes, User usuario){
        this.id = id;
        this.questoes = questoes;
        this.user = usuario;
        this.data = LocalDateTime.now();
    }

    public Simulado()
    {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }
}
