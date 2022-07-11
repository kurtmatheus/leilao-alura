package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeilaoPage {
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	private WebDriver browser;
	
	public LeilaoPage(WebDriver browser) {
		this.browser = browser;;
	}

	public void fechar() {
		this.browser.quit();		
	}

	public boolean isPaginaLeilao() {
		return browser.getCurrentUrl().equals(URL_LEILOES);

	}

	public String getNomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (Exception e) {
			return null;
		}
	}

	public void navegaParaLances() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}
	 
	public CadastroLeilaoPage carregaFormularioNovoLeilao() {
		this.browser.findElement(By.id("novo_leilao_link")).click();
		return new CadastroLeilaoPage(browser);
	}
}
