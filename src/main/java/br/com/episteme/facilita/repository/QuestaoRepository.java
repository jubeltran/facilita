package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

}
