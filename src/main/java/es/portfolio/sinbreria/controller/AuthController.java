package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Usuario;
import es.portfolio.sinbreria.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /*@GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/register";
    }*/

    @GetMapping("/register")
    public String showRegistroPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuario) {
        userService.saveUsuario(usuario);
        return "redirect:/login";
    }


/*    @PostMapping("/register")
    public String procesarRegistro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        // Aquí iría la lógica para guardar el usuario en la base de datos

        model.addAttribute("successMessage", "Registro exitoso!");
        return "register";
    }*/
}

