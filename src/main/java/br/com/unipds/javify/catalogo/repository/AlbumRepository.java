package br.com.unipds.javify.catalogo.repository;

import br.com.unipds.javify.catalogo.domain.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
    Page<Album> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
//    List<Album> findByGeneroContainingIgnoreCase(String genero);
//    List<Album> findByAnoLancamentoGreaterThan(Integer ano);
}
