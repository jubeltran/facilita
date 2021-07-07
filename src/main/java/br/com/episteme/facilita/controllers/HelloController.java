package br.com.episteme.facilita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

    @GetMapping("/inicial")
    public ModelAndView inicial() {
        ModelAndView mv = new ModelAndView("index");  // nome do arquivo html a ser renderizado/exibido
        return mv;  // o Spring vai renderizar o arquivo templates/inicial.html
    }
}
