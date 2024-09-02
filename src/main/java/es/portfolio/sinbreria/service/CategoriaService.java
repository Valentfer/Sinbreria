package es.portfolio.sinbreria.service;
import es.portfolio.sinbreria.entity.Categoria;
import es.portfolio.sinbreria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria existingCategoria = getCategoriaById(id);
        if (existingCategoria != null) {
            existingCategoria.setNombre(categoria.getNombre());
            existingCategoria.setDescripcion(categoria.getDescripcion());
            return categoriaRepository.save(existingCategoria);
        }
        return null;
    }

    public void deleteCategoria(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            categoriaRepository.delete(categoriaOptional.get());
        } else {
            System.out.println("Categoria no encontrada con id: " + id);
        }
    }
}
