package br.com.episteme.facilita.controllers;

import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @GetMapping("/usuarios")
    public ModelAndView index(){

        List<User> usuarios = this.userRepository.findAll();

        ModelAndView mv = new ModelAndView("usuarios/lista");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("usuarios/login");
        return mv;
    }

    @PostMapping("/usuarios")
    public ModelAndView create(@Valid RequisicaoNovoUser requisicao, BindingResult bindingResult){
        System.out.println(requisicao);


        if(bindingResult.hasErrors()){
            System.out.println("\n ************* Tem ERROS *********\n");
            ModelAndView mv = new ModelAndView("usuarios/cadastro");
            return mv;

        }
        else {
            User u = requisicao.toUser();
            this.userRepository.save(u);

            return new ModelAndView("redirect:/usuarios/lista");
        }
    }

}
