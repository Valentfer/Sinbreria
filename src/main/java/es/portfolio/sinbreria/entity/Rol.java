package es.portfolio.sinbreria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Entity
@Table(name = "roles")
public class Rol implements GrantedAuthority {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    public Rol() {}

    @Override
    public String getAuthority() {
        return authority;
    }

}

