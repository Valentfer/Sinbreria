package es.portfolio.sinbreria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_libros",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();

    public Libro() {
    }

    public Libro(Categoria categoria, String isbn, String autor, String titulo) {
        this.categoria = categoria;
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
    }

    // Método para agregar un usuario sin modificar la lista directamente
    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.getLibros().add(this);
    }

    // Método para eliminar un usuario sin modificar la lista directamente
    public void removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.getLibros().remove(this);
    }
}

