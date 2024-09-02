package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Libro;
import es.portfolio.sinbreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("libros", libroService.findAll());
        return "libros/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("libro", libroService.findById(id));
        return "libros/show";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("libro", new Libro());
        return "libros/create";
    }

    @PostMapping
    public String create(@ModelAttribute Libro libro) {
        libroService.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("libro", libroService.findById(id));
        return "libros/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Libro libro) {
        libro.setId(id);
        libroService.save(libro);
        return "redirect:/libros";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        libroService.deleteById(id);
        return "redirect:/libros";
    }
}


