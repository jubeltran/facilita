package br.com.episteme.facilita.service;

import br.com.episteme.facilita.Exceptions.EmailExistsException;
import br.com.episteme.facilita.Exceptions.ServiceExc;
import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;
import br.com.episteme.facilita.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@Service
public class ServiceUser {

    @Autowired
    private UserRepository userRepository;

    public boolean validarEmail(RequisicaoNovoUser requisicao) throws EmailExistsException, NoSuchAlgorithmException {
        List<User> usuarios = userRepository.findAll();
        for (User usuario : usuarios) {
            if (requisicao.getEmail().equals(usuario.getEmail())) {
                return false;
            }
        }
            return true;
    }

    public void salvarUsuario(RequisicaoNovoUser requisicao) throws NoSuchAlgorithmException {
        User user = requisicao.toUser();
        user.setSenha(Util.md5(user.getSenha()));
        userRepository.save(user);
    }

    public User loginUser(String email, String senha) {
        User userLogin = userRepository.findByLogin(email, senha);
        return userLogin;
    }
}
