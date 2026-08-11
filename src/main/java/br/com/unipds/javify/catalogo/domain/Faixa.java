package br.com.unipds.javify.catalogo.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Faixa (

    @Id String id,
    String titulo,
    Integer duracaoSegundos,
    String arquivoAudioUrl,
    String isrc,
    Long totalReproducoes,
    Boolean conteudoExplicito,
    String letraUrl,
    Integer bpm,
    Double energia,
    Boolean instrumental,
//    AlbumResumoFaixa album,
    List<ArtistaParticipante> artistas
) {}
