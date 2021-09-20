package br.com.episteme.facilita.controllers;

import br.com.episteme.facilita.dto.RequisicaoNovaQuestao;
import br.com.episteme.facilita.dto.RequisicaoNovoCurso;
import br.com.episteme.facilita.models.*;
import br.com.episteme.facilita.repository.CursoRepository;
import br.com.episteme.facilita.repository.QuestaoRepository;
import br.com.episteme.facilita.repository.UserRepository;
//import br.com.episteme.facilita.service.ServiceCurso;
import br.com.episteme.facilita.service.ServiceCurso;
import br.com.episteme.facilita.service.ServiceSimulado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ServiceCurso serviceCurso;

    @GetMapping("/geral")
    public ModelAndView geralCursos() {
        ModelAndView mv = new ModelAndView("usuarios/geral");
        return mv;
    }

    @GetMapping("curso/{nome}")
    public ModelAndView pagCurso(@PathVariable String nome, @AuthenticationPrincipal User usuario) {
        ModelAndView mv = new ModelAndView("usuarios/curso");
        Curso curso = cursoRepository.findByNome(nome);
        mv.addObject("curso", curso);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/cadcursos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView cadCursos(RequisicaoNovoCurso requisicaoNovoCurso) {
        ModelAndView mv = new ModelAndView("admin/cadcursos");
        mv.addObject("tipos", TipoDeCurso.values());
        return mv;
    }

    @PostMapping("/salvarCurso")
    public ModelAndView salvar(@Valid RequisicaoNovoCurso requisicaoNovoCurso, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("admin/cadcursos");
        System.out.println(requisicaoNovoCurso);
        mv.addObject("requisicao", requisicaoNovoCurso);
        if (!br.hasErrors()) {
            serviceCurso.salvarCurso(requisicaoNovoCurso);
            mv.addObject("mensagem", "Curso cadastrado!");
            return mv;
        }
        return mv;
    }

    @GetMapping("/cursos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView questoes() {
        List<Curso> cursos = cursoRepository.findAll();
        ModelAndView mv = new ModelAndView("admin/cursos");
        mv.addObject("cursos", cursos);
        return mv;
    }

    @PostMapping("/addfav")
    public ModelAndView addFav(@AuthenticationPrincipal User usuario, String fav){
        serviceCurso.addFavorito(usuario, fav);
        ModelAndView mv = new ModelAndView("usuarios/perfil");
        mv.addObject("usuario", usuario);
        return mv;
    }

}
