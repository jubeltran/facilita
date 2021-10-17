package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.Gabarito;
import br.com.episteme.facilita.models.Simulado;
import br.com.episteme.facilita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GabaritoRepository extends JpaRepository<Gabarito, Long> {
    @Query("select x from Gabarito x where x.simulado = :simulado AND x.user = :usuario")
    public Gabarito findBySimuladoAndUser(Optional<Simulado> simulado, User usuario);
}
