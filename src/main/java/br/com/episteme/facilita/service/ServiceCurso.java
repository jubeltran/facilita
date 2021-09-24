package br.com.episteme.facilita.service;

import br.com.episteme.facilita.dto.RequisicaoNovoCurso;
import br.com.episteme.facilita.models.Curso;
import br.com.episteme.facilita.models.TipoDeCurso;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceCurso {

    @Autowired
    private CursoRepository cursoRepository;

    public void salvarCurso(RequisicaoNovoCurso requisicaoNovoCurso) {
        Curso curso = new Curso(
                requisicaoNovoCurso.getNome(),
                requisicaoNovoCurso.getDescricao(),
                requisicaoNovoCurso.getInsta(),
                requisicaoNovoCurso.getUniversidade1(),
                requisicaoNovoCurso.getUniversidade2(),
                requisicaoNovoCurso.getUniversidade3(),
                requisicaoNovoCurso.getCorteSisu(),
                requisicaoNovoCurso.getCorteFuvest(),
                requisicaoNovoCurso.getTipoDeCurso()
                );
        cursoRepository.save(curso);
    }

    public void addFavorito(User usuario, String fav){
        if(usuario.getFav1().equals(null)){
            usuario.setFav1(fav);
        }
        else if(usuario.getFav2().equals(null)){
            usuario.setFav2(fav);
        }
        else{
            usuario.setFav3(fav);
        }
    }
}
