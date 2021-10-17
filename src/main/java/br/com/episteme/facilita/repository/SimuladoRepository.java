package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimuladoRepository extends JpaRepository<Simulado, Long> {
    @Query("select x from Simulado x where x.id = :id")
    Simulado getById(Integer id);
}
