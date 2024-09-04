package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Usuario;
import es.portfolio.sinbreria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttrs) {


        if (userService.authenticate(username, password)) {
            return "redirect:/usuarios/index";
        } else {
            redirectAttrs.addFlashAttribute("error", "Credenciales incorrectas");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String showRegistroPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           RedirectAttributes redirectAttrs) {
        if (!password.equals(confirmPassword)) {
            redirectAttrs.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/register";
        }

        if (userService.findByUsername(username).isPresent()) {
            redirectAttrs.addFlashAttribute("error", "El nombre de usuario ya existe");
            return "redirect:/register";
        }

        Usuario newUser = new Usuario();
        newUser.setUsername(username);
        newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        userService.saveUsuario(newUser);

        redirectAttrs.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/login";
    }
}

