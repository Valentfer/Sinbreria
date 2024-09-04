package es.portfolio.sinbreria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.portfolio.sinbreria.entity.Usuario;
import es.portfolio.sinbreria.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUsuario(Usuario usuario) {
        String passwd = usuario.getPassword();
        String encodedPasswd = passwordEncoder.encode(passwd);
        usuario.setPassword(encodedPasswd);
        usuarioRepository.save(usuario);
    }


    public boolean authenticate(String username, String password) {
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(username).orElse(null);
        return usuario != null && BCrypt.checkpw(password, usuario.getPassword());}

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Object> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}

