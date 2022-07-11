package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.LeilaoPage;

public class LoginPage {
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;
	
	public LoginPage() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
		this.browser = new ChromeDriver();
		browser.navigate().to("http://localhost:8080/leiloes");
		browser.navigate().to(URL_LOGIN);
	}

	public void fechar() {
		this.browser.quit();		
	}

	public void preencheFormularioLogin(String username, String pass) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(pass);
	}

	public LeilaoPage efetuarLogin() {
		browser.findElement(By.id("login-form")).submit();
		return new LeilaoPage(browser);
	}

	public boolean isPaginaLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);

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

	public boolean isPaginaLoginComErro() {
		return browser.getCurrentUrl().equals(URL_LOGIN+"?error");
	}
	
	
}
