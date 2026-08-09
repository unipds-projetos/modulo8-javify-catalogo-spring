package br.com.unipds.javify.catalogo.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Field;

public record Faixa(
        @NotNull(message = "O número da faixa é obrigatório.")
        @Min(value = 1, message = "O número da faixa deve ser maior que zero.")
        Integer numero,

        @NotBlank(message = "O título da faixa não pode estar em branco.")
        String titulo,

        @NotNull(message = "A duração da faixa é obrigatória.")
        @Min(value = 1, message = "A duração deve ter pelo menos 1 segundo.")
        @Field("duracao_segundos")
        Integer duracaoSegundos ) {}

