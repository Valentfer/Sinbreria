package es.portfolio.sinbreria.repository;


import es.portfolio.sinbreria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);

}
