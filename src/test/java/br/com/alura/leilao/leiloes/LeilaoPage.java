package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class LeilaoPage extends PageObject{
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	
	public LeilaoPage(WebDriver browser) {
		super(browser);
	}
	
	public boolean isPaginaLeilao() {
		return this.browser.getCurrentUrl().equals(URL_LEILOES);
	}

	public String getNomeUsuarioLogado() {
		try {
			return this.browser.findElement(By.id("usuario-logado")).getText();
		} catch (Exception e) {
			return null;
		}
	}

	public void navegaParaLances() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean contemTexto(String texto) {
		return this.browser.getPageSource().contains(texto);
	}
	 
	public CadastroLeilaoPage carregaFormularioNovoLeilao() {
		this.browser.findElement(By.id("novo_leilao_link")).click();
		return new CadastroLeilaoPage(this.browser);
	}
}
