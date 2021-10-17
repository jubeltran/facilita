package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.Questao;
import br.com.episteme.facilita.models.TipoDeProva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    @Query("select x from Questao x where x.tipoDeProva = :tipoDeProva")
    public List<Questao> findByTipoDeProva(TipoDeProva tipoDeProva);

    @Query("select x from Questao x where x.disciplina = :disciplina")
    public Questao findByDisciplina(Disciplina disciplina);

    @Query("select x from Questao x where x.disciplina = :disciplina AND x.tipoDeProva = :tipoDeProva")
    public List<Questao> findByDisciplinaAndTipoDeProva(Disciplina disciplina, TipoDeProva tipoDeProva);

    @Query("select x from Questao x where x.id = :idquestao")
    public Questao getById(Integer idquestao);

}
