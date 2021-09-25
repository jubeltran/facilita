package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Curso;
import br.com.episteme.facilita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("select x from Curso x where x.nome = :nome")
    public Curso findByNome(String nome);

    @Query("select x from Curso x where x.tipoDeCurso = :tipoDeCurso")
    public Curso findByTipoDeCurso(String tipoDeCurso);
}
