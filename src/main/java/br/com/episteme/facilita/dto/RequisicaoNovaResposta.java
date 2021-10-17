package br.com.episteme.facilita.dto;
import br.com.episteme.facilita.models.Alternativa;
import br.com.episteme.facilita.models.Questao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class RequisicaoNovaResposta {
    @NotNull
    private HashMap<String, String> solution;

    public RequisicaoNovaResposta(HashMap<String, String> solution) {
        this.solution = solution;
    }

    public HashMap<String, String> getSolution() {
        return solution;
    }

    public void setSolution(HashMap<String, String> solution) {
        this.solution = solution;
    }
}

