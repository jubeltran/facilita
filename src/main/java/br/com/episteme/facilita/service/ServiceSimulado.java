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

    public Simulado simuladoDiagnostico(TipoDeProva tipoDeProva, User usuario) {
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        ArrayList<Questao> selecionadas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            List<Questao> todas = questaoRepository.findByDisciplinaAndTipoDeProva(disciplina, tipoDeProva);
            selecionadas.add(todas.get(0));
            selecionadas.add(todas.get(1));
        }
        Simulado simuladoDiagnostico = new Simulado(1L, selecionadas);
        simuladoRepository.save(simuladoDiagnostico);
        usuario.setJaRealizouDiagnostico(true);
        return simuladoDiagnostico;
    }

    public Gabarito salvarRespostasDiagnostico(RequisicaoNovaResposta requisicaoNovaResposta, User usuario, Long simuladoId){
        Set<String> chaves = requisicaoNovaResposta.getSolution().keySet();
        ArrayList<Resposta> respostas = new ArrayList<>();
        Simulado simulado = simuladoRepository.getById(simuladoId.longValue());
        for (String chave : chaves) {
            Questao q = questaoRepository.getById(Long.valueOf(chave));
            Alternativa a = alternativaRepository.getById(Long.valueOf(requisicaoNovaResposta.getSolution().get(chave)));
            Resposta r = new Resposta(a, q);
            respostas.add(r);
            respostaRepository.save(r);
        }
        Gabarito gab = new Gabarito(simulado, respostas, usuario);
        gabaritoRepository.save(gab);
        return gab;
    }

    public List<Disciplina> identificarDificuldades(Gabarito gab){
        List<Disciplina> disciplinas = Arrays.asList(Disciplina.values());
        ArrayList<Disciplina> dificuldades = new ArrayList<>();
        for(Disciplina d : disciplinas){
            if(gab.contarErros(d) > gab.contarAcertos(d)){
                dificuldades.add(d);
            }
        }
        return dificuldades;
    }

    public ArrayList<Simulado> gerarSimuladoSugerido(List<Disciplina> dificuldades, User usuario){
        ArrayList<Questao> selecionadas = new ArrayList<>();
        ArrayList<Simulado> simulados = new ArrayList<>();
        TipoDeProva tipoDeProva = usuario.getFoco();
        for(Disciplina d : dificuldades){
            List<Questao> todas = questaoRepository.findByDisciplinaAndTipoDeProva(d, tipoDeProva);
            Collections.shuffle(todas);
            selecionadas.add(todas.get(0));
            selecionadas.add(todas.get(1));
            Simulado simulado = new Simulado(selecionadas, usuario);
            simulados.add(simulado);
        }
        return simulados;
    }
}




