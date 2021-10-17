package br.com.episteme.facilita.service;

import br.com.episteme.facilita.dto.RequisicaoNovaAlternativa;
import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.dto.RequisicaoNovaResposta;
import br.com.episteme.facilita.models.*;
import br.com.episteme.facilita.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ServiceSimulado {

    int i = 0;

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private SimuladoRepository simuladoRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private GabaritoRepository gabaritoRepository;

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

    public Simulado simuladoDiagnostico(TipoDeProva tipoDeProva) {
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        ArrayList<Questao> selecionadas = new ArrayList<>();
        List<Questao> todas = questaoRepository.findByDisciplinaAndTipoDeProva(Disciplina.Biologia, tipoDeProva);
        selecionadas.add(todas.get(0));
        selecionadas.add(todas.get(1));
        /*for (Disciplina disciplina : disciplinas) {
            List<Questao> todas = questaoRepository.findByDisciplinaAndTipoDeProva(disciplina, tipoDeProva);
            selecionadas.add(todas.get(0));
            selecionadas.add(todas.get(1));
        }*/
        Simulado simuladoDiagnostico = new Simulado(selecionadas);
        simuladoRepository.save(simuladoDiagnostico);
        return simuladoDiagnostico;
    }

    public void salvarRespostasDiagnostico(HashMap<Integer, Integer> solutions, User usuario, Integer simuladoId){
        Set<Integer> chaves = solutions.keySet();
        ArrayList<Resposta> respostas = new ArrayList<>();
        Simulado simulado = simuladoRepository.getById(simuladoId);
        for (Integer chave : chaves) {
            Questao q = questaoRepository.getById(chave);
            Alternativa a = alternativaRepository.findByQuestaoAndId(chave, chave.intValue());
            Resposta r = new Resposta(a, q);
            respostas.add(r);
            respostaRepository.save(r);
        }
        Gabarito gabarito = new Gabarito(simulado, respostas, usuario);
        gabaritoRepository.save(gabarito);
    }

    public List<Disciplina> identificarDificuldades(User usuario){
        Optional<Simulado> simuladoDiagnostico = simuladoRepository.findById(1L);
        Gabarito gabarito = gabaritoRepository.findBySimuladoAndUser(simuladoDiagnostico, usuario);
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        ArrayList<Disciplina> dificuldades = new ArrayList<>();
        for(Disciplina d : disciplinas){
            if(gabarito.contarErros(d) > gabarito.contarAcertos(d)){
                dificuldades.add(d);
            }
        }
        return dificuldades;
    }
}

//Gabi
  /*  public Simulado simuladoSugerido(User usuario) {
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        ArrayList<Resposta> erradas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            List<Resposta> selecionadas = questaoRepository.findByDisciplinaAndTipoDeProva(disciplina, usuario.getFoco());
            erradas.add(erradas.get(0));
        }
        Simulado simuladoSugerido = new Simulado(erradas, usuario);
        simuladoRepository.save(simuladoSugerido);
        return simuladoSugerido;
    } */


    //Resposta - p uma questao, alternativa
    //Gabarito - um simulado, um usuario, uma data, list de respostas


