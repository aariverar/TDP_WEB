package com.tsoft.bot.frontend.steps.movistar;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import com.tsoft.bot.frontend.utility.Sleeper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

import static com.tsoft.bot.frontend.pageobject.movistar.movistarfija.AltasNuevasPageObject.*;
import static com.tsoft.bot.frontend.pageobject.movistar.movistarfija.LoginPageObject.*;

public class MovistarFija {

	private static final String EXCEL_WEB = "excel/MovistarFija.xlsx";
	private static final String LOGIN_WEB = "Login";
	private static final String COLUMNA_USUARIO = "Usuario";
	private static final String COLUMNA_CONTRASENIA = "Contraseña";
	private static final String COLUMNA_TIPO_DOCUMENTO = "Tipo Documento";
	private static final String COLUMNA_NUMERO_DOCUMENTO = "Numero Documento";
	private static final String COLUMNA_DEPARTAMENTO = "Departamento";
	private static final String COLUMNA_PROVINCIA = "Provincia";
	private static final String COLUMNA_DISTRITO = "Distrito";
	private static final String COLUMNA_APELLIDO_CLIENTE = "Apellido Cliente";
	private static final String COLUMNA_NOMBRE_CLIENTE = "Nombre Cliente";
	private static final String COLUMNA_FECHA_NACIMIENTO_CLIENTE = "Fecha Nacimiento Cliente";
	private static final String COLUMNA_DEPARTAMENTO_CLIENTE = "Departamento Cliente";
	private static final String COLUMNA_PROVINCIA_CLIENTE = "Provincia Cliente";
	private static final String COLUMNA_DISTRITO_CLIENTE = "Distrito Cliente";
	private static final String COLUMNA_NOMBRE_MADRE_CLIENTE = "Nombre Madre Cliente";
	private static final String COLUMNA_NOMBRE_PADRE_CLIENTE = "Nombre Padre Cliente";
	private static final String COLUMNA_LUGAR_UBICACION = "Lugar Ubicación";
	private static final String COLUMNA_NOMBRE_PRODUCTO = "Nombre Producto";
	private static final String COLUMNA_TIPO_PRODUCTO = "Tipo Producto";
	public static final String CAMPO_REEMPLAZAR = "*Field*";
	private static final String URL_MOVISTAR_FIJA   = "http://tdp-web-venta-fija-qa.mybluemix.net/acciones";
	private static GenerateWord generateWord = new GenerateWord();
	private WebDriver driver;

	public MovistarFija() {
		this.driver = Hook.getDriver();
	}

	@Given("^Ir a la pagina de Movistar Fija$")
	public void irALaPaginaDeMovistarFija() throws Throwable {
		try {
			driver.get(URL_MOVISTAR_FIJA);
			Sleeper.Sleep(1900);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se inició correctamente la página Movistar");
			generateWord.sendText("Se inició correctamente la página Movistar");
			generateWord.addImageToWord(driver);
		}catch (Exception e){
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@And("^Ingresar codigo ATIS / Usuario \"([^\"]*)\"$")
	public void ingresarCodigoATISUsuario(String casoDePrueba) throws Throwable {
		try {
			int movistarFija = Integer.parseInt(casoDePrueba) - 1;
			driver.findElement(By.xpath(TXT_CODIGO_ATIS_USUARIO)).clear();
			String codigoAtis = getData().get(movistarFija).get(COLUMNA_USUARIO);
			driver.findElement(By.xpath(TXT_CODIGO_ATIS_USUARIO)).sendKeys(codigoAtis);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el usuario : " + codigoAtis);
			generateWord.sendText("Se ingresó el codigo ATIS / Usuario ");
			generateWord.addImageToWord(driver);
		}catch (Exception e){
			ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@And("^Click en el boton Continuar$")
	public void clickEnElBotonContinuar() throws Exception {
		try {
			driver.findElement(By.xpath(BTN_CONTINUAR_)).click();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Continuar'");
		}catch (Exception e){
			ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@When("^Validar si el codigo ATIS y ingresar contraseña \"([^\"]*)\"$")
	public void validarSiElCodigoATISYIngresarContraseña(String casoDePrueba) throws Throwable {
		try {
			int movistarFija = Integer.parseInt(casoDePrueba) - 1;
			String contrasenia = getData().get(movistarFija).get(COLUMNA_CONTRASENIA);
			String getUsuario = driver.findElement(By.xpath(TXT_CODIGO_ATIS_USUARIO)).getAttribute("value");

			if (getUsuario.equals(getData().get(movistarFija).get(COLUMNA_USUARIO))) {
				driver.findElement(By.id(BTN_CONTRASENIA)).sendKeys(contrasenia);
				ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó la contraseña'");
			} else {
				driver.findElement(By.id(TXT_CODIGO_ATIS_USUARIO)).sendKeys(getData().get(movistarFija).get(COLUMNA_USUARIO));
				ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el usuario : " + getData().get(movistarFija).get(COLUMNA_USUARIO));

				driver.findElement(By.id(BTN_CONTRASENIA)).sendKeys(contrasenia);
				ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó la contraseña'");
			}
			generateWord.sendText("Se ingresó el usuario y contraseña");
			generateWord.addImageToWord(driver);
		}catch (Exception e){
				ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
			}

	}

	@And("^Click en el boton Iniciar Sesion$")
	public void clickEnElBotonIniciarSesion() throws Exception {
		try {
			driver.findElement(By.xpath(BTN_INICIAR_SESION)).click();
			Sleeper.Sleep(3000);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Iniciar Sesión'");
			generateWord.sendText("Se dió click en el boton 'Iniciar Sesión'");
			generateWord.addImageToWord(driver);
		}catch (Exception e){
			ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}

	}

	@And("^Seleccionar en el menu Altas Nuevas$")
	public void seleccionarEnElMenuAltasNuevas() throws Throwable {
		try {
			driver.findElement(By.xpath(BTN_ALTAS_NUEVAS)).click();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en la opción 'Altas Nuevas'");
			Sleeper.Sleep(1000);
			generateWord.sendText("Se dió click en la opción 'Altas Nuevas'");
			generateWord.addImageToWord(driver);
		}catch (Exception e){
			ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@And("^Click en el boton Obviar y continuar$")
	public void clickEnElBotonObviarYContinuar() throws Exception {
		try {
			scrollBar();
			driver.findElement(By.xpath(BTN_OBVIAR_CONTINUAR)).click();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Obviar y Continuar'");
			generateWord.sendText("Se dió click en el boton 'Obviar y Continuar'");
			generateWord.addImageToWord(driver);
			Sleeper.Sleep(1000);
		}catch (Exception e){
			ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}

	}

	@When("^Ingresar datos del cliente \"([^\"]*)\"$")
	public void ingresarDatosDelCliente(String casoDePrueba) throws Throwable {
		try {
			int movistarFija = Integer.parseInt(casoDePrueba) - 1;
			String tipoDocumento = getData().get(movistarFija).get(COLUMNA_TIPO_DOCUMENTO);
			Select select = new Select(driver.findElement(By.id(CBO_TIPO_DOCUMENTO)));
			select.selectByVisibleText(tipoDocumento);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Tipo Documento : " + tipoDocumento);
			String numeroDocumento = getData().get(movistarFija).get(COLUMNA_NUMERO_DOCUMENTO);
			switch (tipoDocumento) {
				case "DNI":
					driver.findElement(By.id(TXT_DNI)).sendKeys(numeroDocumento);
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el Número Documento : " + numeroDocumento);

					break;
				case "Carné de Extranjeria":
					driver.findElement(By.id(TXT_CE)).sendKeys(numeroDocumento);
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el Número Documento : " + numeroDocumento);
					break;
				case "Pasaporte":
					driver.findElement(By.id(TXT_PASAPORTE)).sendKeys(numeroDocumento);
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el Número Documento : " + numeroDocumento);
					break;
				case "RUC":
					driver.findElement(By.id(TXT_RUC)).sendKeys(numeroDocumento);
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el Número Documento : " + numeroDocumento);
					break;
				case "Otros Extranjeros - Aut. SNM":
					driver.findElement(By.id(TXT_OTROS)).sendKeys(numeroDocumento);
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el Número Documento : " + numeroDocumento);
					break;
			}
			String departamento = getData().get(movistarFija).get(COLUMNA_DEPARTAMENTO);
			Select select2 = new Select(driver.findElement(By.id(CBO_DEPARTAMENTO)));
			select2.selectByVisibleText(departamento);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Departamento : " + departamento);
			Sleeper.Sleep(500);

			String provincia = getData().get(movistarFija).get(COLUMNA_PROVINCIA);
			Select select3 = new Select(driver.findElement(By.id(CBO_PROVINCIA)));
			select3.selectByVisibleText(provincia);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Pronvicia : " + provincia);
			Sleeper.Sleep(500);

			String distrito = getData().get(movistarFija).get(COLUMNA_DISTRITO);
			Select select4 = new Select(driver.findElement(By.id(CBO_DISTRITO)));
			select4.selectByVisibleText(distrito);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Distrito : " + distrito);
			Sleeper.Sleep(1000);
			generateWord.sendText("Se ingresó los datos del cliente");
			generateWord.addImageToWord(driver);
			scrollBar();
			driver.findElement(By.xpath(BTN_EVALUAR)).click();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Evaluar'");
			scrollBar();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Continuar'");
			generateWord.sendText("Se dió en el botón 'Continuar'");
			generateWord.addImageToWord(driver);
			WebDriverWait wait = new WebDriverWait(driver, 60, 100);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(BTN_CONTINUAR_DC)
			)).sendKeys(Keys.ENTER);

			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "PASS");
		}catch (Exception e){

			//ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@When("^Validar servicio reniec, consultar nuevamente \"([^\"]*)\"$")
	public void validarServicioReniecConsultarNuevamente(String casoDePrueba) throws Throwable {
		try {
			int count = 0;

			WebDriverWait wait = new WebDriverWait(driver, 20);

			generateWord.sendText("Se muestra servicio reniec, consultar nuevamente");
			generateWord.addImageToWord(driver);

			while (driver.findElement(By.xpath(BTN_CONSULTAR_NUEVAMENTE)).isDisplayed()) {

					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BTN_CONSULTAR_NUEVAMENTE))).click();

					count++;

					if (count == 2) {
						break;
					} if (count == 1 && !driver.findElement(By.xpath(BTN_CONSULTAR_NUEVAMENTE)).isDisplayed()) {
						break;
					} else {
						continue;
					}

				}


		} catch (Exception e) {
			//ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}

	}

	@And("^Ingresar datos de validacion del cliente \"([^\"]*)\"$")
	public void ingresarDatosDeValidacionDelCliente(String casoDePrueba) throws Throwable {
		int movistarFija = Integer.parseInt(casoDePrueba) - 1;
		WebDriverWait wait = new WebDriverWait(driver, 50);

		if (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CBO_NOMBRE_MADRE))).isDisplayed()){
			Select select2 = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CBO_NOMBRE_MADRE))));
			Sleeper.Sleep(200);
			select2.selectByVisibleText("ALEJANDRINA ROSA");
			Sleeper.Sleep(300);
			driver.switchTo().defaultContent();
			Sleeper.Sleep(2000);
			scrollBar();
			driver.findElement(By.xpath("//a[text()='SIGUIENTE']")).click();
			Sleeper.Sleep(2000);
		}

	}

	@And("^Ingresar lugar de ubicacion \"([^\"]*)\"$")
	public void ingresarLugarDeUbicacion(String casoDePrueba) throws Throwable {
		try {
			int movistarFija = Integer.parseInt(casoDePrueba) - 1;
			driver.switchTo().activeElement();
			driver.switchTo().defaultContent();
			Sleeper.Sleep(300);
			String lugarUbicacion = getData().get(movistarFija).get(COLUMNA_LUGAR_UBICACION);
			driver.findElement(By.id(TXT_LUGAR_UBICACION)).sendKeys(lugarUbicacion);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó lugar ubicación : " + lugarUbicacion);
			//INSPECCIONAR
			driver.findElement(By.xpath(TXT_LUGAR_UBICACION)).sendKeys(Keys.ENTER);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Ubicar Mapa'");
			Sleeper.Sleep(500);

		}catch (Exception e){
			//ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@And("^Click en el boton Continuar, Lugar Ubicacion$")
	public void clickEnElBotonContinuarLugarUbicacion() throws Exception {
		try {

			Sleeper.Sleep(1500);
			scrollBar();
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//a[contains(text(),'CONTINUAR')]")).click();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió click en el boton 'Continuar'");
			generateWord.sendText("Se ingresó la ubicación correctamente");
			generateWord.addImageToWord(driver);
		}catch (Exception e){
			//ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
			generateWord.sendText("Tiempo de espera ha excedido");
			generateWord.addImageToWord(driver);
		}
	}

	@When("^Seleccionar tipo de producto y nombre de producto \"([^\"]*)\"$")
	public void seleccionarTipoDeProductoYNombreDeProducto(String casoDePrueba) throws Throwable {
		try {
			int movistarFija = Integer.parseInt(casoDePrueba) - 1;
			String typeProduct = getData().get(movistarFija).get(COLUMNA_TIPO_PRODUCTO);
			String productName = getData().get(movistarFija).get(COLUMNA_NOMBRE_PRODUCTO);
			switch (typeProduct) {
				case "TRÍO":
					driver.findElement(By.xpath(TRIO)).click();
					Sleeper.Sleep(1500);
					scrollBar();
					int count3 = 0;
					List listRow3 = driver.findElements(By.xpath(XPATH_TRIO_ULTRA));
					for (int i = 0; i < listRow3.size(); i++) {
						WebElement contestName = (WebElement) listRow3.get(i);
						String getName = contestName.getText();
						count3++;
						if (getName.equalsIgnoreCase(productName)) {
							driver.findElement(By.xpath("//div[@class='trio_inf acordeon'][" + count3 + "]//a")).click();
						}
					}
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Tipo de Producto : " + typeProduct + ", y se eligió el producto : " + productName);
					generateWord.sendText("Se elegió el Tipo de Producto : " + typeProduct + ", y se eligió el producto : " + productName);
					generateWord.addImageToWord(driver);
					break;
				case "DÚO":
					driver.findElement(By.xpath(DUO)).click();
					Sleeper.Sleep(1500);
					scrollBar();

					int count2 = 0;
					List listRow2 = driver.findElements(By.xpath(XPATH_DUO_ULTRA));
					for (int i = 0; i < listRow2.size(); i++) {
						WebElement contestName = (WebElement) listRow2.get(i);
						String getName = contestName.getText();
						count2++;
						if (getName.equalsIgnoreCase(productName)) {
							driver.findElement(By.xpath(XPATH_DUO.replace(CAMPO_REEMPLAZAR, Integer.toString(count2)))).click();
							generateWord.sendText("Se elegió el Tipo de Producto : " + typeProduct + ", y se eligió el producto : " + productName);
							generateWord.addImageToWord(driver);
						}
					}

					ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Tipo de Producto : " + typeProduct + ", y se eligió el producto : " + productName);

					break;

				case "MONO":
					driver.findElement(By.xpath(MONO)).click();
					Sleeper.Sleep(1500);
					scrollBar();
					int count = 0;
					List listRow = driver.findElements(By.xpath(XPATH_MONO_ULTRA));
					for (int i = 0; i < listRow.size(); i++) {
						WebElement contestName = (WebElement) listRow.get(i);
						String getName = contestName.getText();
						count++;
						if (getName.equalsIgnoreCase(productName)) {
							driver.findElement(By.xpath("//div[@class='mono_inf acordeon'][" + count + "]//a")).click();
						}
					}
					ExtentReportUtil.INSTANCE.stepPass(driver, "Se elegió el Tipo de Producto : " + typeProduct + ", y se eligió el producto : " + productName);
					generateWord.sendText("Se elegió el Tipo de Producto : " + typeProduct + ", y se eligió el producto : " + productName);
					generateWord.addImageToWord(driver);
					break;
			}

			driver.switchTo().defaultContent();
			Sleeper.Sleep(1500);
			driver.findElement(By.xpath("//a[text()='CONTINUAR']")).click();
			ExtentReportUtil.INSTANCE.stepPass(driver, "Click en el boton Continuar");

			generateWord.sendText("Click en el boton Continuar");
			generateWord.addImageToWord(driver);
			Sleeper.Sleep(1500);
			driver.findElement(By.xpath("//img[@class='user']")).click();

			Sleeper.Sleep(1500);
			driver.findElement(By.xpath("//a[@id='cierrate']")).click();
			Sleeper.Sleep(1500);
			ExtentReportUtil.INSTANCE.stepPass(driver, "Click en el boton Cerrar sesión");
			generateWord.sendText("Cerramos sesión");
			generateWord.addImageToWord(driver);
			driver.switchTo().alert().accept();
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "PASS");
		}catch (Exception e){
			//ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
			ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
		}
	
	}

	private List<HashMap<String, String>> getData() throws Throwable {
		return ExcelReader.data(EXCEL_WEB, LOGIN_WEB);
	}

	protected void scrollBar() {
		JavascriptExecutor ev = (JavascriptExecutor)driver;
		ev.executeScript("window.scrollBy(0, 720)");
	}



}
