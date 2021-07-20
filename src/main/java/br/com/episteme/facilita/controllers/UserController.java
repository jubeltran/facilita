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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
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

    @GetMapping("/saibaMais")
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView("saibaMais");
        return mv;
    }


    @PostMapping("/salvarUsuario")
    public ModelAndView create(@Valid RequisicaoNovoUser requisicao, BindingResult br) throws Exception {
        System.out.println(requisicao);
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        if (br.hasErrors()) {
            return mv;
        }

        if(userService.validarEmail(requisicao)){
            userService.salvarUsuario(requisicao);
            return login();
        }
        else{
            mv.addObject("mensagem", "Já existe um usuário cadastrado para esse email");
            return mv;
        }
    }


    @PostMapping("/login")
    public ModelAndView login(@Valid User user, BindingResult br, HttpSession session) throws ServiceExc, NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());

        if(br.hasErrors()){
            mv.setViewName("usuarios/login");
        }

        if(userService.loginUser(user.getEmail(),Util.md5(user.getSenha())) == null){
            mv.addObject("mensagem", "Usuário não encontrado ou senha inválida");
            return mv;
        } else
        {
           return index();
        }
    }
}
