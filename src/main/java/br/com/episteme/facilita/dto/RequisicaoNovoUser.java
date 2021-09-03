package br.com.episteme.facilita.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


// É uma classe DTO (Data Transfer Object)
public class RequisicaoNovoUser {
    @NotBlank
    private String nome; // em caso de erro, NotBlank.requisicaoNovoProfessor.nome
    @NotBlank
    private String email;
    @NotBlank @Size(min=6,max=12)
    private String senha;

    public RequisicaoNovoUser(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "RequisicaoNovoUser{" +
                "nome='" + this.nome + '\'' +
                ", email=" + this.email +
                '}';
    }
}
