package br.com.episteme.facilita.service;

import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.AppUserRole;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ServiceUser implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public boolean validarEmail(RequisicaoNovoUser requisicao){
        List<User> usuarios = userRepository.findAll();
        for (User usuario : usuarios) {
            if (requisicao.getEmail().equals(usuario.getEmail())) {
                return false;
            }
        }
            return true;
    }

    public void salvarUsuario(RequisicaoNovoUser requisicao) {
        if (requisicao.getEmail().equals("episteme.equipe@gmail.com")) {
            User admin = new User(
                    requisicao.getNome(),
                    requisicao.getEmail(),
                    requisicao.getSenha(),
                    AppUserRole.ADMIN
            );
            String encodedPassword = bCryptPasswordEncoder
                    .encode(admin.getSenha());
            admin.setSenha(encodedPassword);

            userRepository.save(admin);

        } else {
            User user = new User(
                    requisicao.getNome(),
                    requisicao.getEmail(),
                    requisicao.getSenha(),
                    AppUserRole.USER
            );

            String encodedPassword = bCryptPasswordEncoder
                    .encode(user.getSenha());
            user.setSenha(encodedPassword);

            userRepository.save(user);
        }
    }
}
