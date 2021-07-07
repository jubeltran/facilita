package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository< User, Long> {
}
