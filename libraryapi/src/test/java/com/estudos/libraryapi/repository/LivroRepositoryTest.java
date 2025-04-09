package com.estudos.libraryapi.repository;


import com.estudos.libraryapi.model.Autor;
import com.estudos.libraryapi.model.GeneroLivro;
import com.estudos.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarLivro(){
        Livro livro = new Livro();
        livro.setIsbn("123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Aventuras de um programador");
        livro.setDataPublicacao(LocalDate.of(2025,2,17));

        Autor autor = autorRepository
                .findById(UUID.fromString("61370aa8-9c15-4d22-8fa8-1813880c9d52"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarLivroCascade(){
        Livro livro = new Livro();
        livro.setIsbn("123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Cascade");
        livro.setDataPublicacao(LocalDate.of(2025,2,17));

        Autor autor = new Autor();
        autor.setNome("Cascade");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1980, 1, 1));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("8541ce63-748d-455f-ab58-6796e8e5a028");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("cccba7cb-fa34-4c05-aa51-626183162fcb");
        Autor autorCascade = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autorCascade);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("f5c45e88-c8c6-47c7-8321-95c501dc46d0");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("8541ce63-748d-455f-ab58-6796e8e5a028");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional // abre uma operação no banco de dados e fecha no final do método, utilizado com o fetch
    void buscarLivro(){
        UUID id = UUID.fromString("f5c45e88-c8c6-47c7-8321-95c501dc46d0");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }


}