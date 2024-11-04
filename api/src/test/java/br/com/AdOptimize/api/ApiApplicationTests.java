package br.com.AdOptimize.api;

import br.com.AdOptimize.api.model.Anuncio;
import br.com.AdOptimize.api.model.Campanha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiApplicationTests {

	private Campanha campanha;
	private Anuncio anuncio1;
	private Anuncio anuncio2;

	@BeforeEach
	public void setUp() {
		// Criação da campanha
		campanha = new Campanha();
		campanha.setNome("Campanha de anuncios");

		// Criação dos anuncios
		anuncio1 = new Anuncio();
		anuncio1.setTitulo("Iphone");
		anuncio1.setPreco(20.00);
		anuncio1.setDescricao("Telefone atual");
		anuncio1.setCampanha(campanha);

		anuncio2 = new Anuncio();
		anuncio2.setTitulo("SAMSUNG A22");
		anuncio2.setPreco(10.00);
		anuncio2.setDescricao("Telefone antigo");
		anuncio2.setCampanha(campanha);

		// Associando anuncios a campanha
		campanha.getAnuncios().add(anuncio1);
		campanha.getAnuncios().add(anuncio2);
	}

	@Test
	public void testCampanhaAnunciosAssociation() {
		// Verifica se o professor possui 2 alunos
		assertEquals(2, campanha.getAnuncios().size());

		// Verifica se os alunos estão associados corretamente ao professor
		assertEquals( campanha, anuncio1.getCampanha());
		assertEquals( campanha, anuncio2.getCampanha());
	}

	@Test
	public void testCampanhaNome() {
		// Verifica se o nome da campanha está correto
		assertEquals("Campanha de anuncios", campanha.getNome());
	}

	@Test
	public void testAnuncioNome() {
		// Verifica se os nomes dos alunos estão corretos
		assertEquals("Iphone", anuncio1.getTitulo());
		assertEquals("SAMSUNG A22", anuncio2.getTitulo());
	}
}
