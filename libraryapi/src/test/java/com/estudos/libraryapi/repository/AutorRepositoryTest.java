package com.estudos.libraryapi.repository;

import com.estudos.libraryapi.model.Autor;
import com.estudos.libraryapi.model.GeneroLivro;
import com.estudos.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired //ingestão de dependência
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarAutor(){
        Autor autor = new Autor();
        autor.setNome("Fulano");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 1, 1));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo" + autorSalvo);
    }

    @Test
    public void atualizarAutor(){
        var id = UUID.fromString("81d33ace-b5a1-465a-a882-44e8f06e39f6");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor" + autorEncontrado);

            autorEncontrado.setNacionalidade("Italiano");
            autorRepository.save(autorEncontrado);
        }
        System.out.println(possivelAutor.get());

    }

    @Test
    public void listarAutor(){
        List<Autor> listaAutor = autorRepository.findAll();
        listaAutor.forEach(System.out::println);
    }

    @Test
    public void countAutor(){
        System.out.println("Contagem de autores " + autorRepository.count());

    }

    @Test
    public void deletarAutorId(){
        //funciona mas tem uma forma mais fácil
//        var idAutor = UUID.fromString("2f62a5b5-59e7-4501-974f-7456ea0efa0f");
//
//        Optional<Autor> possivelAutor = autorRepository.findById(idAutor);
//
//        if(possivelAutor.isPresent()){
//            autorRepository.delete(possivelAutor.get());
//        }
//        else {
//            System.out.println("Autor não encontrado");
//    }
        var idAutor = UUID.fromString("81d33ace-b5a1-465a-a882-44e8f06e39f6");
        autorRepository.deleteById(idAutor);
    }

    @Test
    public void deleteAutorObjeto(){
        var idAutor = UUID.fromString("aa5ff956-7875-4f7e-bc67-55e597bfaa42");
        var fulano = autorRepository.findById(idAutor).get();
        autorRepository.delete(fulano);

    }

    @Test
    void salvarAutorComLivrosTests(){
        Autor autor = new Autor();
        autor.setNome("Rique");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 1, 1));

        Livro livro = new Livro();
        livro.setIsbn("123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Livro Novo");
        livro.setDataPublicacao(LocalDate.of(2025,2,17));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("123");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Livro Ciencia");
        livro2.setDataPublicacao(LocalDate.of(2025,2,17));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());



    }
}
