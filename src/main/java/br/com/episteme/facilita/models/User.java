package br.com.episteme.facilita.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @NotBlank
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String email;
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String senha;

    public User(){

    }

    public User(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public User toUser(){
        User user = new User();
        user.setNome(this.nome);
        user.setEmail(this.email);
        user.setSenha(this.senha);
        return user;
    }

}
