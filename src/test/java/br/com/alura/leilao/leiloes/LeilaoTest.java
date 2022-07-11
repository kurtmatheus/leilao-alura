package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {
	
	private LeilaoPage paginaDeLeilao;
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeilao.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioLogin("fulano", "pass");
		this.paginaDeLeilao = paginaDeLogin.efetuarLogin();
		CadastroLeilaoPage paginaCadastroLeilao = paginaDeLeilao.carregaFormularioNovoLeilao();
		
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do Dia " + hoje;
		String valor = "500.00";
		
		this.paginaDeLeilao = paginaCadastroLeilao.cadastrarLeilao(nome, valor, hoje);
		Assert.assertTrue(paginaCadastroLeilao.isLeilaoCadastrado(nome, valor, hoje));
	}
}
