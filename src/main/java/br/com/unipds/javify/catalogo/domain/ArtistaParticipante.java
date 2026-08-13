package br.com.unipds.javify.catalogo.domain;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ArtistaParticipante(
        @NotBlank(message = "O nome do artista é obrigatório.")
        String nome,

        @NotBlank(message = "A função do artista é obrigatória.")
        String funcao ) implements Serializable {}

