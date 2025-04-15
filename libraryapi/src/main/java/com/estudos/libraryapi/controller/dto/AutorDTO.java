package com.estudos.libraryapi.controller.dto;

import com.estudos.libraryapi.model.Autor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatório.") // para strings, não venha nula e nem vazia
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão, máximo 100.")
        String nome,

        @NotNull(message = "Campo obrigatório.") // pode vir nula mas não pode vir vazia
        @Past(message = "Não pode ser uma data futura.")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão, máximo 50.")
        String nacionalidade)
{

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }
}
