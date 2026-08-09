package br.com.unipds.javify.catalogo.service;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.exception.AlbumNaoEncontradoException;
import br.com.unipds.javify.catalogo.repository.AlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Page<Album> listarTodos(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    public Page<Album> buscaPorTitulo(String titulo, Pageable pageable) {
        return albumRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }

    public Album buscarPorId(String id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNaoEncontradoException(id));
    }

    public Album salvarNovoAlbum(Album novoAlbum) {
        return albumRepository.save(novoAlbum);
    }

    public Album atualizarAlbum(String id, Album albumAtualizado) {
        buscarPorId(id);
        if (!id.equals(albumAtualizado.id())) {
            throw new AlbumNaoEncontradoException(albumAtualizado.id());
        }
        return albumRepository.save(albumAtualizado);
    }

    public void excluirAlbum(String id) {
        if (!albumRepository.existsById(id)) {
            throw new AlbumNaoEncontradoException(id);
        }
        albumRepository.deleteById(id);
    }
}
