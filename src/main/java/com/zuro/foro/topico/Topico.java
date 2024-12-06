package com.zuro.foro.topico;

import com.zuro.foro.controller.TopicoActualizar;
import com.zuro.foro.controller.TopicoRegistro;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechadeCreacion;
    private boolean status;
    private String autor;
    private String curso;

    public Topico(){}

    public Topico(@Valid TopicoRegistro topicoRegistro) {
        this.titulo = topicoRegistro.titulo();
        this.mensaje = topicoRegistro.mensaje();
        this.fechadeCreacion = LocalDateTime.now();
        this.status = topicoRegistro.status();
        this.autor = topicoRegistro.autor();
        this.curso = topicoRegistro.curso();
    }

    public void actualizar(@Valid TopicoActualizar topicoActualizar) {

        if (topicoActualizar.titulo() != null) {
            this.titulo = topicoActualizar.titulo();
        }

        if (topicoActualizar.mensaje() != null) {
            this.mensaje = topicoActualizar.mensaje();
        }

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechadeCreacion() {
        return fechadeCreacion;
    }

    public boolean isStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public void eliminarMensaje() {
        this.status = false;
    }
}
