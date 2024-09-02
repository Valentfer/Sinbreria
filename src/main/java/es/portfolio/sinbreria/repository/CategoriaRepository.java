package es.portfolio.sinbreria.repository;

import es.portfolio.sinbreria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
