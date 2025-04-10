package com.estudos.libraryapi.controller;

import com.estudos.libraryapi.controller.dto.AutorDTO;
import com.estudos.libraryapi.model.Autor;
import com.estudos.libraryapi.servive.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    // ou @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
        var autorEntidade = autor.mapearParaAutor();
        autorService.salvarAutor(autorEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterAutor(@PathVariable("id") String id) {
        // id dentro do patch variable é opcional mas se incluir, deve ser o mesmo do getmapping
        var idAutor = UUID.fromString(id);

        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
        if(autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(
                    autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
            );
        return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id) {
        // void é porque não estamos utilizando nada no "body"
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if(autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletarAutor(autorOptional.get());

        return ResponseEntity.noContent().build();
    }

}
