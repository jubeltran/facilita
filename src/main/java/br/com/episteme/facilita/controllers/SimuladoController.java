package br.com.episteme.facilita.controllers;
import br.com.episteme.facilita.dto.RequisicaoNovaAlternativa;
import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.dto.RequisicaoNovaResposta;
import br.com.episteme.facilita.dto.RequisicaoNovoGabarito;
import br.com.episteme.facilita.models.*;
import br.com.episteme.facilita.repository.QuestaoRepository;
import br.com.episteme.facilita.repository.SimuladoRepository;
import br.com.episteme.facilita.service.ServiceSimulado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.*;


@Controller
public class SimuladoController {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private SimuladoRepository simuladoRepository;

    @Autowired
    private ServiceSimulado serviceSimulado;

    private List<Resposta> respostas = null;

    @GetMapping("/cadquestao")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView cadquestao(RequisicaoNovaQuestao requisicaoNovaQuestao) {
        ModelAndView mv = new ModelAndView("admin/cadquestao");
        mv.addObject("disciplinas", Disciplina.values());
        mv.addObject("tipos", TipoDeProva.values());
        return mv;
    }

    @GetMapping("/cadalternativa")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView cadalternativa(RequisicaoNovaAlternativa requisicaoNovaAlternativa) {
        List<Questao> questoes = questaoRepository.findAll();
        ModelAndView mv = new ModelAndView("admin/cadalternativa");
        mv.addObject("questoes", questoes);
        return mv;
    }

    @PostMapping("/salvarQuestao")
    public ModelAndView create(@Valid RequisicaoNovaQuestao requisicaoNovaQuestao, BindingResult br) {
        System.out.println(requisicaoNovaQuestao);
        ModelAndView mv = new ModelAndView("admin/cadquestao");
        if (br.hasErrors()) return mv;
        serviceSimulado.salvarQuestao(requisicaoNovaQuestao);
        mv.addObject("mensagem", "Questão Cadastrada!");
        return mv;
    }

    @PostMapping("/salvarAlternativa")
    public ModelAndView create(RequisicaoNovaAlternativa requisicaoNovaAlternativa, BindingResult br) {
        System.out.println(requisicaoNovaAlternativa);
        ModelAndView mv = new ModelAndView("admin/cadalternativa");
        if (br.hasErrors()) return mv;
        serviceSimulado.salvarAlternativa(requisicaoNovaAlternativa, requisicaoNovaAlternativa.getIdquestao());
        mv.addObject("mensagem", "Alternativa Cadastrada!");
        return mv;
    }

    @GetMapping("/questoes")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView questoes() {
        List<Questao> questoes = questaoRepository.findAll();
        ModelAndView mv = new ModelAndView("admin/questoes");
        mv.addObject("questoes", questoes);
        return mv;
    }

    @PostMapping("/diagnostico")
    public ModelAndView simuladoInicial(@RequestParam TipoDeProva tipoDeProva, @AuthenticationPrincipal User usuario) {
        ModelAndView mv = new ModelAndView("usuarios/diagnostico");
        usuario.setFoco(tipoDeProva);
        Simulado simuladoDiagnostico = serviceSimulado.simuladoDiagnostico(tipoDeProva, usuario);
        ArrayList<Questao> questoes = new ArrayList<>(simuladoDiagnostico.getQuestoes());
        mv.addObject("questoes", questoes);
        mv.addObject("simuladoId", simuladoDiagnostico.getId());
        return mv;
    }

    @PostMapping("/salvarRespostaDiagnostico")
    public ModelAndView salvarRespostasDiagnostico(@RequestParam HashMap<String, String> solutions, @AuthenticationPrincipal User usuario){
        ModelAndView mv = new ModelAndView("usuarios/diagnostico");
        RequisicaoNovaResposta requisicaoNovaResposta = new RequisicaoNovaResposta(solutions);
        Gabarito gab = serviceSimulado.salvarRespostasDiagnostico(requisicaoNovaResposta, usuario, 1L);
        Integer acertos = gab.contarTotalAcertos();
        List<Disciplina> dificuldades = serviceSimulado.identificarDificuldades(gab);
        List<Simulado> simulados = serviceSimulado.gerarSimuladoSugerido(dificuldades, usuario);
        mv.setViewName("usuarios/sugeridos");
        mv.addObject("acertos", acertos);
        mv.addObject("dificuldades", dificuldades);
        mv.addObject("simulados", simulados);
        return mv;
    }

//    @PostMapping("/sugerido/{simuladoId}")
//    public ModelAndView acessarSimulado(@PathVariable Long simuladoId){
//        ModelAndView mv = new ModelAndView("usuarios/simulado");
//        Simulado simulado =
//        mv.addObject()
//    }
//
//
//    @GetMapping("/{id}/delete")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ModelAndView deletarQuestao(@PathVariable Long id){
//        ModelAndView mv = new ModelAndView("redirect:/questoes");
//        this.questaoRepository.deleteById(id);
//        return mv;
//    }
}
