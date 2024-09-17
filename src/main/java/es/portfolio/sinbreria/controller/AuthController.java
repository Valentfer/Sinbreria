package es.portfolio.sinbreria.controller;

import es.portfolio.sinbreria.entity.Usuario;
import es.portfolio.sinbreria.repository.UserRepository;
import es.portfolio.sinbreria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/")
public class AuthController {

    private final UserService userService;

    private final UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/perform_login")
    public String processLogin(@ModelAttribute Usuario usuario, Model model) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "/usuarios/usuario";
        } catch (Exception e) {
            logger.error("Error al autenticar usuario", e);
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }


    @GetMapping("/register")
    public String showRegistroPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("successMessage", "");
        model.addAttribute("errorMessage", "");
        return "/register";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           RedirectAttributes redirectAttrs) {

        try {
            if (userRepository.findByUsername(username).isPresent()) {
                throw new Exception("El nombre de usuario ya existe");
            }

            if (!password.equals(confirmPassword)) {
                redirectAttrs.addFlashAttribute("error", "La contraseña no coincide");
                return "redirect:/register";
            }else {

                Usuario newUser = new Usuario();
                newUser.setUsername(username);
                newUser.setPassword(password);
                userService.saveUsuario(newUser);

                redirectAttrs.addFlashAttribute("success", "Usuario registrado correctamente");
                return "redirect:/login";
            }
        } catch (Exception e) {
            logger.error("Error al registrar usuario: ", e);
            redirectAttrs.addFlashAttribute("error", "El nombre de usuario ya existe");
            return "redirect:/register";
        }
    }

}

