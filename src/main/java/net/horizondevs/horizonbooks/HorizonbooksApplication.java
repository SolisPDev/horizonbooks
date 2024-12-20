package net.horizondevs.horizonbooks;

import net.horizondevs.horizonbooks.principal.Principal;
import net.horizondevs.horizonbooks.repository.AutorRepository;
import net.horizondevs.horizonbooks.repository.LibrosRepository;
import net.horizondevs.horizonbooks.services.ConsumoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HorizonbooksApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository librosRepository;
	@Autowired
	private AutorRepository autorRepository;


	public static void main(String[] args) {
		SpringApplication.run(HorizonbooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepository, autorRepository);
		principal.menuPrincipal();
	}
}
