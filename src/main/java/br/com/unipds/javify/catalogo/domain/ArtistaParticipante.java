package br.com.unipds.javify.catalogo.domain;

import jakarta.validation.constraints.NotBlank;

public record ArtistaParticipante(
        @NotBlank(message = "O nome do artista é obrigatório.")
        String nome,

        @NotBlank(message = "A função do artista é obrigatória.")
        String funcao ) {}

