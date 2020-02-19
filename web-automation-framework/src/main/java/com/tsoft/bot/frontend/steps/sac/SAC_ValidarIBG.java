package com.tsoft.bot.frontend.steps.sac;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import com.tsoft.bot.frontend.utility.Sleeper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.HashMap;
import java.util.List;

import static com.tsoft.bot.frontend.pageobject.sac.PageObject_SAC.*;

public class SAC_ValidarIBG{

    private static final String EXCEL_WEB = "excel/Interact.xlsx";
    private static final String SAC_WEB = "SAC";
    private static final String COLUMNA_URL = "URL";
    private static final String COLUMNA_USUARIO = "USUARIO";
    private static final String COLUMNA_PASSWORD = "PASSWORD";
    private static final String COLUMNA_TELEFONO = "TELEFONO";

    private static GenerateWord generateWord = new GenerateWord();
    private WebDriver driver;

    public SAC_ValidarIBG() { this.driver = Hook.getDriver();}

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, SAC_WEB);
    }


    @Given("^Ingreso a la url de SAC \"([^\"]*)\"$")
    public void ingresoALaUrlDeSAC(String casoDePrueba) throws Throwable {
        try {
            int sac = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(sac).get(COLUMNA_URL);
            driver.get(url);
            Sleeper.Sleep(3500);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se inició correctamente la página de Interact");
            generateWord.sendText("Se inició correctamente la página de Interact");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @And("^se ingresa el usuario \"([^\"]*)\"$")
    public void seIngresaElUsuario(String casoDePrueba) throws Throwable {
        try {
            int sac = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.id(TXT_USUARIO)).clear();
            String usuario = getData().get(sac).get(COLUMNA_USUARIO);
            driver.findElement(By.id(TXT_USUARIO)).sendKeys(usuario);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el usuario : " + usuario);
            generateWord.sendText("Se ingresó el usuario "+ usuario);
            generateWord.addImageToWord(driver);

        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^la contrasenia \"([^\"]*)\"$")
    public void laContrasenia(String casoDePrueba) throws Throwable {
        try {
            int sac = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.id(TXT_CONTRASENIA)).clear();
            String pass = getData().get(sac).get(COLUMNA_PASSWORD);
            driver.findElement(By.id(TXT_CONTRASENIA)).sendKeys(pass);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó la contrasenia : " + pass);
            generateWord.sendText("Se ingresó la contrasenia "+ pass);
            generateWord.addImageToWord(driver);

        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^se da click en el boton Entrar$")
    public void seDaClickEnElBotonEntrar() throws Exception {
        try {
            driver.findElement(By.xpath(BTN_ENTRAR)).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón Entrar");
            generateWord.sendText("Se dió clic en el botón Entrar ");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @When("^se da click en Consulta de Ordenes$")
    public void seDaClickEnConsultaDeOrdenes() throws Exception {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath(BTN_BANDEJA))).build().perform();
            driver.findElement(By.xpath(BTN_CONSULTAORDENES)).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón Consultar Orden");
            generateWord.sendText("Se dió clic en el botón Consultar Orden");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^se ingresa el numero de celular \"([^\"]*)\"$")
    public void seIngresaElNumeroDeCelular(String casoDePrueba) throws Throwable {
        try {
            int sac = Integer.parseInt(casoDePrueba) - 1;
            String telef = getData().get(sac).get(COLUMNA_TELEFONO);
            driver.findElement(By.id(TXT_CELULAR)).clear();
            driver.findElement(By.id(TXT_CELULAR)).sendKeys(telef);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el teléfono: "+telef);
            generateWord.sendText("Se ingresó el teléfono: "+telef);
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^se filtra la busqueda por antiguedad Una Semana$")
    public void seFiltraLaBusquedaPorAntiguedadUnaSemana() throws Exception {
        try {
            driver.findElement(By.xpath(LIST_ANTIGUADAD)).click();
            driver.findElement(By.xpath(SELECT_UNASEMANA)).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se selecciona antigudad : Una Semana");
            generateWord.sendText("Se selecciona antigudad : Una Semana");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @Then("^se da click en el boton Buscar Ordenes$")
    public void seDaClickEnElBotonBuscarOrdenes() throws Exception {
        try {
            driver.findElement(By.id(BTN_BUSCARORDENES)).click();
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.sendKeys(Keys.PAGE_DOWN).build().perform();
            Thread.sleep(10000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se da click en el botón Buscar Ordenes");
            generateWord.sendText("Se da click en el botón Buscar Ordenes");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @When("^se da click en Gestor Ordenes IBG$")
    public void seDaClickEnGestorOrdenesIBG() throws Exception {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath(BTN_SEARCHIBG))).build().perform();
            driver.findElement(By.xpath(BTN_GESTORDIBG)).click();
            Thread.sleep(8000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se da click en el botón Gestor de Ordenes IBG");
            generateWord.sendText("Se da click en el botón Gestor de Ordenes IBG");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    @And("^se ingresa el numero de celular IBG\"([^\"]*)\"$")
    public void seIngresaElNumeroDeCelularIBG(String casoDePrueba) throws Throwable {
        try {
            int sac = Integer.parseInt(casoDePrueba) - 1;
            String telef = getData().get(sac).get(COLUMNA_TELEFONO);
            driver.findElement(By.xpath(LIST_FILTROAVAN)).click();
            driver.findElement(By.xpath(SELECT_TELEFONO)).click();
            driver.findElement(By.id(TXT_CELULAR2)).clear();
            driver.findElement(By.id(TXT_CELULAR2)).sendKeys(telef);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el teléfono: "+telef);
            generateWord.sendText("Se ingresó el teléfono: "+telef);
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }


    @Then("^se da click en el boton Buscar Ordenes \"([^\"]*)\"$")
    public void seDaClickEnElBotonBuscarOrdenes(String arg0) throws Throwable {
        try {
            driver.findElement(By.id(BTN_CONSULTARORDENES2)).click();
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.sendKeys(Keys.PAGE_DOWN).build().perform();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se da click en el botón Buscar Ordenes");
            generateWord.sendText("Se da click en el botón Buscar Ordenes");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, SAC_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }


}
