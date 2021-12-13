package br.com.episteme.facilita.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="\"user\"")

public class User implements UserDetails {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Column(nullable = false)
    private String senha;
    @Column
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    @Column
    private String fav1;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Gabarito> gabaritos;
    private TipoDeProva foco;
    private boolean jaRealizouDiagnostico;

    public User(User user) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.appUserRole = appUserRole;
    }

    public User(String nome, String email, String senha, AppUserRole appUserRole) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.appUserRole = appUserRole;
    }

    public User(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getEmail(){ return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() {
        return nome;
    }

    public String getFav1() {
        return fav1;
    }

    public void setFav1(String fav1) { this.fav1 = fav1; }

    public TipoDeProva getFoco(){ return foco; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    public List<Gabarito> getGabaritos() {
        return gabaritos;
    }

    public void setGabaritos(List<Gabarito> gabaritos) {
        this.gabaritos = gabaritos;
    }

    public void setFoco(TipoDeProva foco) {
        this.foco = foco;
    }

    public boolean isJaRealizouDiagnostico() {
        return jaRealizouDiagnostico;
    }

    public void setJaRealizouDiagnostico(boolean jaRealizouDiagnostico) {
        this.jaRealizouDiagnostico = jaRealizouDiagnostico;
    }
}


