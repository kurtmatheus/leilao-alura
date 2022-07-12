package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {
	
	private LeilaoPage paginaLeilao;
	private CadastroLeilaoPage paginaCadastroLeilao;
	
	@BeforeEach
	public void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioLogin("fulano", "pass");
		this.paginaLeilao = paginaDeLogin.efetuarLogin();
		this.paginaCadastroLeilao = paginaLeilao.carregaFormularioNovoLeilao();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaLeilao.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {	
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do Dia " + hoje;
		String valor = "500.00";
		
		this.paginaLeilao = paginaCadastroLeilao.cadastrarLeilao(nome, valor, hoje);
		Assert.assertTrue(paginaCadastroLeilao.isLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	public void deveriaValidarCadastroLeilao() {
		this.paginaLeilao = paginaCadastroLeilao.cadastrarLeilao("", "", "");
		Assert.assertFalse(paginaCadastroLeilao.isPaginaAtual());
		Assert.assertTrue(paginaCadastroLeilao.isPaginaLeilao());
		Assert.assertTrue(paginaCadastroLeilao.isMensagensDeValidacaoVisiveis());

	}
}
