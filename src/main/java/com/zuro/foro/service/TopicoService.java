package com.zuro.foro.service;

import com.zuro.foro.controller.TopicoActualizar;
import com.zuro.foro.repository.TopicoRepository;
import com.zuro.foro.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public List<Topico> listar() {
        return repository.findAll();
    }

    public Topico guardar(Topico nuevoTopico) {
        return repository.save(nuevoTopico);
    }


}
