package br.com.unipds.javify.catalogo.repository;

import br.com.unipds.javify.catalogo.domain.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
