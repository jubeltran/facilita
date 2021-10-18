package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Alternativa;

import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.Questao;
import br.com.episteme.facilita.models.TipoDeProva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long>{

    @Query("select x from Alternativa x where x.id = :id")
    Alternativa getById(Long id);
}