package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    @Query("select x from Questao x where x.tipoDeProva = :tipoDeProva")
    public ArrayList<Questao> findByTipoDeProva(String tipoDeProva);

    @Query("select x from Questao x where x.disciplina = :disciplina")
    public Questao findByDisciplina(String disciplina);

    @Query("select x from Questao x where x.disciplina = :disciplina AND x.tipoDeProva = :tipoDeProva")
    public Questao findByDisciplinaAndTipoDeProva(String disciplina, String tipoDeProva);

}
