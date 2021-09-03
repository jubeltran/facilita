package br.com.episteme.facilita.controllers;

import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;
import br.com.episteme.facilita.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUser userService;

    @GetMapping("/home")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");  // nome do arquivo html a ser renderizado/exibido
        return mv;  // o Spring vai renderizar o arquivo templates/index.html
    }

    @GetMapping("/admin/usuarios")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView lista() {
        List<User> usuarios = userRepository.findAll();
        ModelAndView mv = new ModelAndView("usuarios/lista");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar(RequisicaoNovoUser requisicao) {
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("usuarios/login");
        return mv;
    }

    @GetMapping("/saibaMais")
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView("saibaMais");
        return mv;
    }

    @GetMapping("/perfil")
    public static ModelAndView perfil(@AuthenticationPrincipal User usuario){
        System.out.println(usuario);
        ModelAndView mv = new ModelAndView("usuarios/perfil");
        mv.addObject("usuario", usuario);
        return mv;
    }


    @PostMapping("/salvarUsuario")
    public ModelAndView create(@Valid RequisicaoNovoUser requisicao, BindingResult br) throws Exception {
        System.out.println(requisicao);
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        if (br.hasErrors()) {
            return mv;
        }

        if (userService.validarEmail(requisicao)) {
            userService.salvarUsuario(requisicao);
            return login();
        } else {
            mv.addObject("mensagem", "Já existe um usuário cadastrado com esse email");
            return mv;
        }
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView deletarUsuario(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("redirect:/admin/usuarios");
        this.userRepository.deleteById(id);
        return mv;
    }
}

