package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Alternativa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long>{

}