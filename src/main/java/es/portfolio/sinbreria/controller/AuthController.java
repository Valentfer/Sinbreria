package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/login";
    }

    @PostMapping("/login")
    public String processLogin(User user, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/dashboard";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(Model model) {
        SecurityContextHolder.clearContext();
        model.addAttribute("message", "Has salido de la sesión correctamente");
        return "login";
    }

    @GetMapping("/register")
    public String showRegistroPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("successMessage", "");
        model.addAttribute("errorMessage", "");
        return "/register";
    }



/*
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes redirectAttrs) {

        if (userService.findByUsername(username).isPresent()) {
            redirectAttrs.addFlashAttribute("error", "El nombre de usuario ya existe");
            return "redirect:/register";
        } else {

            Usuario newUser = new Usuario();
            newUser.setUsername(username);
            newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            userService.saveUsuario(newUser);

            redirectAttrs.addFlashAttribute("success", "Usuario registrado correctamente");
            return "redirect:/login";
        }

    }
*/
    @GetMapping("/index")
    public String index() {
        return "index";
    }

}

