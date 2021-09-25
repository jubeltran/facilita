package br.com.episteme.facilita.service;

import br.com.episteme.facilita.dto.RequisicaoNovaAlternativa;
import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.models.*;
import br.com.episteme.facilita.repository.AlternativaRepository;
import br.com.episteme.facilita.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void salvarAlternativa(RequisicaoNovaAlternativa requisicaoNovaAlternativa, Long idquestao) {
        Optional<Questao> questaoOptional = questaoRepository.findById(idquestao);
        Alternativa a = new Alternativa(
                requisicaoNovaAlternativa.isCerta(),
                requisicaoNovaAlternativa.getTexto(),
                questaoOptional.get()
                );
        alternativaRepository.save(a);
    }

    public ArrayList<Questao> simuladoDiagnostico(String tipoDeProva) {
        ArrayList<Questao> questoes = new ArrayList<>();
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        for(Disciplina disciplina : disciplinas){
            questoes.add(questaoRepository.findByDisciplinaAndTipoDeProva(disciplina.name(), tipoDeProva));
        }
        return questoes;
    }

}

