package br.com.episteme.facilita.models;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Alternativa {

    @NotNull
    private Long id;
    private boolean certa = false;
    @NotBlank @Size(max = 10000)
    private String texto;

    public Alternativa(){

    }

    public Alternativa(boolean certa, String texto) {
        this.certa = certa;
        this.texto = texto;
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

    @Id
    public Long getId() {
        return id;
    }
}
