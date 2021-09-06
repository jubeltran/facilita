package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.Questao;
import br.com.episteme.facilita.models.TipoDeProva;
import br.com.episteme.facilita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    @Query("select x from Questao x where x.tipoDeProva = :tipoDeProva")
    public Questao findByTipoDeProva(TipoDeProva tipoDeProva);

    @Query("select x from Questao x where x.disciplina = :disciplina")
    public Questao findByDisciplina(Disciplina disciplina);
}
