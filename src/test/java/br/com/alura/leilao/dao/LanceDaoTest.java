package br.com.alura.leilao.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LanceBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;

public class LanceDaoTest {
	
	private LanceDao dao;
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.dao = new LanceDao(em);
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}
	
	@Test
	void deveriaTrazerMaiorLanceDoLeilao() {
		Usuario fulano = new UsuarioBuilder()
							.comEmail("fulano@email.com")
							.comNome("fulano")
							.comSenha("123123").criar();
		em.persist(fulano);
		
		Usuario beltrano = new UsuarioBuilder()
							.comEmail("beltrano@email.com")
							.comNome("beltrano")
							.comSenha("123123").criar();
		em.persist(beltrano);
		
		Usuario ciclano = new UsuarioBuilder()
							.comEmail("ciclano@email.com")
							.comNome("ciclano")
							.comSenha("123123").criar();
		em.persist(ciclano);
		
		Leilao leilao = new LeilaoBuilder()
							.comNome("Leilao Teste 14/07")
							.comValorInicial("50.0")
							.comData(LocalDate.now())
							.comUsuario(fulano).criar();
		
		leilao = new LeilaoDao(em).salvar(leilao);
		
		Lance lance1 = new LanceBuilder()
							.comUsuario(beltrano)
							.comValor("80.0").criar();		
		
		Lance lance2 = new LanceBuilder()
							.comUsuario(ciclano)
							.comValor("100.0").criar();
		
		leilao.propoe(lance1);
		dao.salvar(lance1);
		leilao.propoe(lance2);		
		dao.salvar(lance2);
		
		leilao = new LeilaoDao(em).salvar(leilao);		
		
		Lance maiorLance = dao.buscarMaiorLanceDoLeilao(leilao);		
		Assert.assertEquals(new BigDecimal("100.0"), maiorLance.getValor());
	}
}
