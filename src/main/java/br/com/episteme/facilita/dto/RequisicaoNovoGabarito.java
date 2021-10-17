package br.com.episteme.facilita.dto;

import br.com.episteme.facilita.models.Resposta;
import br.com.episteme.facilita.models.Simulado;
import br.com.episteme.facilita.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class RequisicaoNovoGabarito {
    private LocalDateTime data;
    private List<Resposta> respostas;
    private User usuario;

    public RequisicaoNovoGabarito(List<Resposta> respostas, User usuario) {
        this.data = LocalDateTime.now();
        this.respostas = respostas;
        this.usuario = usuario;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public User getUsuario() {
        return usuario;
    }
}
