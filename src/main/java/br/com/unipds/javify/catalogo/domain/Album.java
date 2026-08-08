package br.com.unipds.javify.catalogo.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record Album (
                     @NotBlank(message = "O título do álbum é obrigatório e não pode estar em branco.")
                     String titulo,

                     @NotNull(message = "O ano de lançamento é obrigatório.")
                     @Min(value = 1900, message = "O ano de lançamento deve ser maior que 1900.")
                     Integer anoLancamento,

                     @NotBlank(message = "O gênero musical é obrigatório.")
                     String genero,

                     @Valid
                     List<ArtistaParticipante> artistas,

                     @Valid
                     List<Faixa> faixas) {

}
