package br.com.episteme.facilita.repository;

import br.com.episteme.facilita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository< User, Long> {
    @Query("select u from User u where u.email = :email AND u.senha = :senha")
    public User findByLogin(String email, String senha);

    @Query("select x from User x where x.email = :email")
    public List<User> findByEmail(String email);
}
