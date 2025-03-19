package br.com.joaoDev.bibliotecaMusica;

import br.com.joaoDev.bibliotecaMusica.main.Main;
import br.com.joaoDev.bibliotecaMusica.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaMusicaApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository artistaRepository;
	//private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaMusicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(artistaRepository);
		main.exibirMenu();

	}
}
