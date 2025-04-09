package com.estudos.libraryapi.servive;

import com.estudos.libraryapi.model.Autor;
import com.estudos.libraryapi.model.GeneroLivro;
import com.estudos.libraryapi.model.Livro;
import com.estudos.libraryapi.repository.AutorRepository;
import com.estudos.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemSave(){
        var livro = livroRepository.
                findById(UUID.fromString("c227f4fc-c101-46a1-b213-beb671499628"))
                .orElse(null);

        livro.setGenero(GeneroLivro.FICCAO);
        // não é necessário -> livroRepository.save(livro); pois já estamos no modo de transação.
    }

    @Transactional
    public void executar(){

        Autor autor = new Autor();
        autor.setNome("Teste Rique");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 1, 1));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("978-3-16-148410-0");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Teste Livro Rique");
        livro.setDataPublicacao(LocalDate.of(2001, 12, 1));
        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Rique")) {
            throw new RuntimeException("Rollback");
        }


    }
}
