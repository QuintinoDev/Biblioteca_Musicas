package br.com.joaoDev.bibliotecaMusica.repository;

import br.com.joaoDev.bibliotecaMusica.model.Artista;
import br.com.joaoDev.bibliotecaMusica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    //Mandando procurar no meu banco se realmete tem o nome digitado
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscarMusicasPorArtista(String nome);

}
