package com.estudos.libraryapi.controller.dto;

import com.estudos.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório.") // para strings, não venha nula e nem vazia
        String nome,
        @NotNull(message = "Campo obrigatório.") // pode vir nula mas não pode vir vazia
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatório.")
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
