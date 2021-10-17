package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Curso;
import br.com.episteme.facilita.models.TipoDeCurso;
import br.com.episteme.facilita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("select x from Curso x where x.nome = :nome")
    public Curso findByNome(String nome);

    @Query("select x from Curso x where x.tipoDeCurso = :tipoDeCurso")
    public List<Curso> findByTipoDeCurso(TipoDeCurso tipoDeCurso);
}
