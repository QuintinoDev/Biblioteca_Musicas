package br.com.joaoDev.bibliotecaMusica.main;
import br.com.joaoDev.bibliotecaMusica.model.Artista;
import br.com.joaoDev.bibliotecaMusica.model.Musica;
import br.com.joaoDev.bibliotecaMusica.model.TipoArtista;
import br.com.joaoDev.bibliotecaMusica.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {
    Scanner leitura = new Scanner(System.in);
    private final ArtistaRepository artistaRepository;
    //private final MusicaRepository musicaRepository;


    public Main(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
        //this.musicaRepository = musicaRepository;
    }

    public void exibirMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                                                
                    5 - Sair
                    
                    Digite a opção desejada:
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar musicas de que artista: ");
        var musicaArtista = leitura.nextLine();
        List<Musica> musicas = artistaRepository.buscarMusicasPorArtista(musicaArtista);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(System.out::println);
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastra musicas de que artista: ");
        var nomeArtista = leitura.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if (artista.isPresent()){
            System.out.println("Informe o titulo da música");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        }else {
            System.out.println("artista não encontrado");
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Infome o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo deste artista: (SOLO, DUPLA ou BANDA)");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            artistaRepository.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
