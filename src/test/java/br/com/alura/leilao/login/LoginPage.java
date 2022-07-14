package br.com.alura.leilao.login;

import org.openqa.selenium.By;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeilaoPage;

public class LoginPage extends PageObject{
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	public LoginPage() {
		super(null);
		this.browser.navigate().to(URL_LOGIN);
	}

	public void preencheFormularioLogin(String username, String pass) {
		this.browser.findElement(By.id("username")).sendKeys(username);
		this.browser.findElement(By.id("password")).sendKeys(pass);
	}

	public LeilaoPage efetuarLogin() {
		this.browser.findElement(By.id("login-form")).submit();
		return new LeilaoPage(this.browser);
	}

	public boolean isPaginaLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);

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

	public boolean isPaginaLoginComErro() {
		return this.browser.getCurrentUrl().equals(URL_LOGIN+"?error");
	}
	
	
}
