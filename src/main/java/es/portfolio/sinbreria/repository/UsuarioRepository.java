package es.portfolio.sinbreria.repository;

import es.portfolio.sinbreria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Object> findByUsername(String username);
}
