package br.com.unipds.javify.catalogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNaoEncontradoException extends RuntimeException {

    public AlbumNaoEncontradoException(String id) {
        super("Álbum com ID '" + id + "' não encontrado no catálogo.");
    }
}
