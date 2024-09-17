package es.portfolio.sinbreria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


    public Libro() {
    }

    public Libro(Categoria categoria, String isbn, String autor, String titulo) {
        this.categoria = categoria;
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
    }

}

