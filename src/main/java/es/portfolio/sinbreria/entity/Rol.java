package es.portfolio.sinbreria.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return "";
    }

    // Implementación de método de GrantedAuthority

    // Constructor, getters y setters


    public Rol() {
    }

    public Rol(Long id, String authority, Set<Usuario> usuarios) {
        this.id = id;
        this.authority = authority;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

