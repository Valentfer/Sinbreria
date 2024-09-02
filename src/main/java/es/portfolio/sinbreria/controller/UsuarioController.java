package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Usuario;
import es.portfolio.sinbreria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("usuario", usuarioService.findAll());
        return "usuario/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.findById(id));
        return "usuarios/show";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/create";
    }

    @PostMapping
    public String create(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.findById(id));
        return "usuarios/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuario.setId(id);
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }
}

