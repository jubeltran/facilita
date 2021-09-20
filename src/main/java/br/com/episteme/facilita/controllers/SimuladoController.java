package br.com.episteme.facilita.controllers;
import br.com.episteme.facilita.dto.RequisicaoNovaAlternativa;
import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.Questao;
import br.com.episteme.facilita.models.TipoDeProva;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.QuestaoRepository;
import br.com.episteme.facilita.service.ServiceSimulado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;


@Controller
public class SimuladoController {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private ServiceSimulado serviceSimulado;

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
        ModelAndView mv = new ModelAndView("admin/cadalternativa");
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
    public ModelAndView create(@Valid RequisicaoNovaAlternativa requisicaoNovaAlternativa, BindingResult br) {
        System.out.println(requisicaoNovaAlternativa);
        ModelAndView mv = new ModelAndView("admin/cadquestao");
        if (br.hasErrors()) return mv;
        serviceSimulado.salvarAlternativa(requisicaoNovaAlternativa);
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

    /*@GetMapping("/diagnostico")
    public ModelAndView simuladoInicial(TipoDeProva tipoDeProva) {
        ModelAndView mv = new ModelAndView("usuarios/diagnostico");
        mv.addObject("simuladoDiagnostico", serviceSimulado.simuladoDiagnostico(tipoDeProva));
        return mv;
    }
     */
}
