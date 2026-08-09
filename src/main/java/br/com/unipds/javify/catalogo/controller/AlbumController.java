package br.com.unipds.javify.catalogo.controller;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<Page<Album>> listaTodos(@RequestParam(required = false) String titulo,
                                                  @PageableDefault(size = 5, page = 0,
                                                          sort = "anoLancamento", direction = Sort.Direction.DESC) Pageable pageable) {
        if (StringUtils.hasText(titulo)) {
            return ResponseEntity.ok(albumService.buscaPorTitulo(titulo, pageable));
        }
        return ResponseEntity.ok(albumService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> detalhaAlbum(@PathVariable String id) {
        Album album = albumService.buscarPorId(id);
        return ResponseEntity.ok(album);
    }

}

