package es.portfolio.sinbreria.service;

import es.portfolio.sinbreria.entity.Libro;
import es.portfolio.sinbreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Libro findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }

}

