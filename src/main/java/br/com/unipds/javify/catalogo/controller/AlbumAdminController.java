package br.com.unipds.javify.catalogo.controller;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/albuns")
public class AlbumAdminController {

    private final AlbumService albumService;

    public AlbumAdminController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Album> criaAlbum(@Valid @RequestBody Album novoAlbum) {
        Album albumSalvo = albumService.salvarNovoAlbum(novoAlbum);
        return ResponseEntity.status(HttpStatus.CREATED).body(albumSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizaAlbum(
            @PathVariable String id,
            @Valid @RequestBody Album albumAtualizado) {
        Album albumSalvo = albumService.atualizarAlbum(id, albumAtualizado);
        return ResponseEntity.ok(albumSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluiAlbum(@PathVariable String id) {
        albumService.excluirAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
