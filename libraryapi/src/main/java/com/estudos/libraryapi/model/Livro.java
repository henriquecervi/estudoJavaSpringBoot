package com.estudos.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
public class Livro {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

//    relacionamento de foreign key
// muitos livros para 1 Autor
    @ManyToOne(
           // cascade = CascadeType.ALL
            fetch = FetchType.LAZY // não carrega o autor junto com o livro, retorna apenas os dados do Livro.
            // padrão é EAGER
    )
    @JoinColumn(name = "id_autor")
    private Autor autor;

}
