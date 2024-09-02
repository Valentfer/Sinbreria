package es.portfolio.sinbreria.repository;

import es.portfolio.sinbreria.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
