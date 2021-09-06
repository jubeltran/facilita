package br.com.episteme.facilita.service;

import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.Questao;
import br.com.episteme.facilita.models.TipoDeProva;
import br.com.episteme.facilita.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ServiceSimulado {

    @Autowired
    private QuestaoRepository questaoRepository;

    public void salvarQuestao(RequisicaoNovaQuestao requisicaoNovaQuestao) {
        Questao questao = new Questao(
                requisicaoNovaQuestao.getTexto(),
                requisicaoNovaQuestao.getA1(),
                requisicaoNovaQuestao.getA2(),
                requisicaoNovaQuestao.getA3(),
                requisicaoNovaQuestao.getA4(),
                requisicaoNovaQuestao.getCerta(),
                requisicaoNovaQuestao.getDisciplina(),
                requisicaoNovaQuestao.getTipoDeProva()
        );
        questaoRepository.save(questao);
    }

    public ArrayList<Questao> simuladoDiagnostico(TipoDeProva tipoDeProva) {
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        List<Questao> questoes = (List<Questao>) questaoRepository.findByTipoDeProva(tipoDeProva);
        ArrayList<Questao> simulado = new ArrayList<>();
        Collections.shuffle(questoes);
        for(Disciplina d : disciplinas){
            Questao q = questaoRepository.findByDisciplina(d);
            for(Questao questao : questoes){
                if(q.equals(questao)){
                    simulado.add(q);
                }
            }
        }
        return simulado;
    }
}

