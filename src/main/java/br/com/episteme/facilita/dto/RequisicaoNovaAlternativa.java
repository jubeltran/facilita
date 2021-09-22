package br.com.episteme.facilita.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequisicaoNovaAlternativa {
    private Boolean certa;
    @NotBlank
    @Size(max = 10000)
    private String texto;
    private Long idquestao;

    public RequisicaoNovaAlternativa(Boolean certa, String texto, Long idquestao) {
        this.certa = false;
        this.texto = texto;
        this.idquestao = idquestao;
    }

    public boolean isCerta() {
        return certa;
    }

    public void setCerta(Boolean certa) {
        this.certa = certa;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getIdquestao() {
        return idquestao;
    }

    public void setIdquestao(Long idquestao) {
        this.idquestao = idquestao;
    }
}
