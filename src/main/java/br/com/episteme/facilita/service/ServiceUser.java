package br.com.episteme.facilita.service;

import br.com.episteme.facilita.Exceptions.EmailExistsException;
import br.com.episteme.facilita.Exceptions.ServiceExc;
import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;
import br.com.episteme.facilita.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                throw new EmailExistsException("Já existe um usuário cadastrado para: " + usuario.getEmail());
            }
        }
        return true;
    }

    public User loginUser(String email, String senha) {
        User userLogin = userRepository.findByLogin(email, senha);
        return userLogin;
    }
}
