package br.com.unipds.javify.catalogo.service;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.exception.AlbumNaoEncontradoException;
import br.com.unipds.javify.catalogo.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> listarTodos() {
        return albumRepository.findAll();
    }

    public List<Album> buscaPorTitulo(String titulo) {
        throw new UnsupportedOperationException("TODO");
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
