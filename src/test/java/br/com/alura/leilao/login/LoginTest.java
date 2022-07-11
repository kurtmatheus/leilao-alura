package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencheFormularioLogin("fulano", "pass");
		paginaDeLogin.efetuarLogin();
		
		Assert.assertFalse(paginaDeLogin.isPaginaLogin());
		Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
	}
	
	
	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.preencheFormularioLogin("inavalido", "123123");
		paginaDeLogin.efetuarLogin();
		
		Assert.assertTrue(paginaDeLogin.isPaginaLoginComErro());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		paginaDeLogin.navegaParaLances();
		Assert.assertTrue(paginaDeLogin.isPaginaLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Novo Leilão"));
	}
}
