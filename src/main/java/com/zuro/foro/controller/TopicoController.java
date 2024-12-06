package com.zuro.foro.controller;

import com.zuro.foro.repository.TopicoRepository;
import com.zuro.foro.service.TopicoService;
import com.zuro.foro.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoService topicoService, TopicoRepository topicoRepository) {
        this.topicoService = topicoService;
        this.topicoRepository = topicoRepository;
    }

    @GetMapping
    public ResponseEntity<Page<TopicoListado>> obtener(@PageableDefault(size = 20) Pageable paginacion) {

        return ResponseEntity.ok(topicoRepository
                .findByStatusTrue(paginacion)
                .map(topico -> new TopicoListado(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechadeCreacion(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
        );

    }

    @PostMapping
    public ResponseEntity<Topico> crear(
            @RequestBody @Valid TopicoRegistro topicoRegistro,
            UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoService.guardar(new Topico(topicoRegistro));
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicoListado> sobreescribir(@RequestBody @Valid TopicoActualizar topicoActualizar) {

        Topico topico = topicoRepository.getReferenceById(topicoActualizar.id());
        topico.actualizar(topicoActualizar);

        return ResponseEntity.ok(

                new TopicoListado(

                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechadeCreacion(),
                        topico.getAutor(),
                        topico.getCurso()

                )
        );

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminarMensaje();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoListado> retornaDatosMedico(@PathVariable Long id){

        Topico topico = topicoRepository.getReferenceById(id);

        var datosTopicos = new TopicoListado(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechadeCreacion(),
                topico.getAutor(),
                topico.getCurso()
        );

        return ResponseEntity.ok(datosTopicos);

    }
}

