package br.com.episteme.facilita.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequisicaoNovaAlternativa {
    private boolean certa = false;
    @NotBlank
    @Size(max = 10000)
    private String texto;

    public RequisicaoNovaAlternativa(boolean certa, String texto) {
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
}
