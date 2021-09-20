package br.com.episteme.facilita.service;

import br.com.episteme.facilita.dto.RequisicaoNovaAlternativa;
import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.models.Alternativa;
import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.Questao;
import br.com.episteme.facilita.models.TipoDeProva;
import br.com.episteme.facilita.repository.AlternativaRepository;
import br.com.episteme.facilita.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ServiceSimulado {

    int i = 0;

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    public void salvarQuestao(RequisicaoNovaQuestao requisicaoNovaQuestao) {
        Questao questao = new Questao(
                requisicaoNovaQuestao.getTexto(),
                requisicaoNovaQuestao.getDisciplina(),
                requisicaoNovaQuestao.getTipoDeProva()
        );
        questaoRepository.save(questao);
    }

    public void salvarAlternativa(RequisicaoNovaAlternativa requisicaoNovaAlternativa) {
        Alternativa a = new Alternativa(
                requisicaoNovaAlternativa.isCerta(),
                requisicaoNovaAlternativa.getTexto()
                );
        alternativaRepository.save(a);
    }

    /*public Questao[] simuladoDiagnostico(TipoDeProva tipoDeProva) {
        Questao[] simulado = new Questao[10];
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        for(Disciplina disciplina : disciplinas){
            simulado[i] = questaoRepository.findQuestao(tipoDeProva, disciplina);
            i++;
        }
        return simulado;
    }*/

}

