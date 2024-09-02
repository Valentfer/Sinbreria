package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Categoria;
import es.portfolio.sinbreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

        @Autowired
        private CategoriaService categoriaService;

        @GetMapping
        public ResponseEntity<List<Categoria>> getAllCategorias() {
            List<Categoria> categorias = categoriaService.getAllCategorias();
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
            Categoria categoria = categoriaService.getCategoriaById(id);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
            Categoria categoriadto = categoriaService.createCategoria(categoria);
            return new ResponseEntity<>(categoriadto, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
            Categoria categoriadto = categoriaService.updateCategoria(id, categoria);
            return new ResponseEntity<>(categoriadto, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.noContent().build();
        }

}
