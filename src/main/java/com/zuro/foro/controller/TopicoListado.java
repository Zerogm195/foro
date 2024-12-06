package com.zuro.foro.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoListado(

        @NotNull String titulo,

        @NotBlank String mensaje,

        LocalDateTime fechadeCreacion,

        @NotBlank String autor,

        @NotNull String curso
) {
}
