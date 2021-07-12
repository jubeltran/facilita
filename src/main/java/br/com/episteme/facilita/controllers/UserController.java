package br.com.episteme.facilita.controllers;

import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;
import br.com.episteme.facilita.Exceptions.ServiceExc;
import br.com.episteme.facilita.service.ServiceUser;
import br.com.episteme.facilita.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
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
        mv.addObject("user", new User());
        return mv;  // o Spring vai renderizar o arquivo templates/index.html
    }

    @GetMapping("/usuarios")
    public ModelAndView lista(){
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

    @PostMapping("/salvarUsuario")
    public ModelAndView create(@Valid RequisicaoNovoUser requisicao, BindingResult br) throws Exception {
        System.out.println(requisicao);
        if (br.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");
            ModelAndView mv = new ModelAndView("usuarios/cadastro");
            return mv;
        }

        boolean validacao = userService.validarEmail(requisicao);
        if(validacao == true){
            User user = requisicao.toUser();
            user.setSenha(Util.md5(user.getSenha()));
            userRepository.save(user);
        }
        return lista();
    }


}
