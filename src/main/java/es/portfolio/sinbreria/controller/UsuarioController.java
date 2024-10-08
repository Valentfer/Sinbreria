package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Libro;
import es.portfolio.sinbreria.entity.Usuario;
import es.portfolio.sinbreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private LibroService libroService;


    @GetMapping("/index")
    public String logout(Model model) {
        SecurityContextHolder.clearContext();
        model.addAttribute("message", "Has salido de la sesión correctamente");
        return "index";
    }

    @GetMapping("/usuario")
    public String indiceUser(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = (Usuario) authentication.getPrincipal();

        model.addAttribute("username", usuario.getUsername());
        model.addAttribute("email", usuario.getId());
        model.addAttribute("libros", usuario.getLibros());

        List<Libro> librosUsuario = libroService.findAll();
        model.addAttribute("libros", librosUsuario);

        return "/usuarios/usuario";
    }

/*

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute @Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/usuarios/edit";
        }
        try {
            usuario.setId(id);
            userService.updateUsuario(usuario);
            model.addAttribute("successMessage", "Usuario actualizado con éxito!");
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error al actualizar el usuario: " + e.getMessage());
            return "/usuarios/edit";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            userService.deleteUsuario(id);
            model.addAttribute("successMessage", "Usuario eliminado con éxito!");
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error al eliminar el usuario: " + e.getMessage());
            return "/usuarios/error";
        }
    }
//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("usuarios", userService.findAllUsuarios());
//       return "/usuarios/index";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable Long id, Model model) {
//        Usuario usuario = userService.findUsuarioById(id);
//        if (usuario == null) {
//            model.addAttribute("error", "Usuario no encontrado");
//            return "/usuarios/error";
//        }
//        model.addAttribute("usuario", usuario);
//        return "/usuarios/show";
//    }
//
//    @GetMapping("/new")
//    public String createForm(Model model) {
//        Usuario usuario = new Usuario();
//        usuario.setUsername("");
//        usuario.setPassword("");
//        model.addAttribute("usuario", usuario);
//        return "/usuarios/create";
//    }
//
///*
//    @PostMapping("/registro")
//    public String create(@ModelAttribute @Valid Usuario usuario, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "/usuarios/create";
//        }
//        try {
//            userService.saveUsuario(usuario);
//            model.addAttribute("successMessage", "Usuario registrado con éxito!");
//            return "redirect:/usuario";
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Ocurrió un error al registrar el usuario: " + e.getMessage());
//            return "/usuarios/create";
//        }
//    }
//*/
//    @GetMapping("/{id}/edit")
//    public String editForm(@PathVariable Long id, Model model) {
//        Usuario usuario = userService.findUsuarioById(id);
//        if (usuario == null) {
//            model.addAttribute("error", "Usuario no encontrado");
//            return "/usuarios/error";
//        }
//        model.addAttribute("usuario", usuario);
//        return "/usuarios/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@PathVariable Long id, @ModelAttribute @Valid Usuario usuario, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "/usuarios/edit";
//        }
//        try {
//            usuario.setId(id);
//            userService.saveUsuario(usuario);
//            model.addAttribute("successMessage", "Usuario actualizado con éxito!");
//            return "redirect:/usuario";
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Ocurrió un error al actualizar el usuario: " + e.getMessage());
//            return "/usuarios/edit";
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Long id, Model model) {
//        try {
//            userService.deleteUsuario(id);
//            model.addAttribute("successMessage", "Usuario eliminado con éxito!");
//            return "redirect:/usuario";
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Ocurrió un error al eliminar el usuario: " + e.getMessage());
//            return "/usuarios/error";
//        }
//    }
}


