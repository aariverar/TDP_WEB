package com.tsoft.bot.frontend.steps.MyStore;

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
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

import static com.tsoft.bot.frontend.pageobject.mystore.MyStorePageObject.*;


public class MyStore {

    private static final String EXCEL_WEB = "excel/MyStore.xlsx";
    private static final String LOGIN_WEB = "Login";
    private static final String COLUMNA_URL = "URL";
    private static final String COLUMNA_CORREO = "Correo";
    private static GenerateWord generateWord = new GenerateWord();
    private WebDriver driver;

    public MyStore() {
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, LOGIN_WEB);
    }

    @Given("^Ingreso a la url de My Store \"([^\"]*)\"$")
    public void ingresoALaUrlDeMyStore(String casoDePrueba) throws Throwable {
        try {
            int myflight = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(myflight).get(COLUMNA_URL);
            driver.get(url);
            Sleeper.Sleep(3500);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se inició correctamente la página MyStore");
            generateWord.sendText("Se inició correctamente la página MyStore");
            generateWord.addImageToWord(driver);
        }catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @When("^doy click en el boton Sign-in$")
    public void doyClickEnElBotonSignIn() throws Exception {
        try {
            driver.findElement(By.xpath(BTN_SIGNIN)).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón SignIN");
            generateWord.sendText("Se dió clic en el botón SignIN ");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^ingreso el correo \"([^\"]*)\"$")
    public void ingresoElCorreo(String casoDePrueba) throws Throwable {
        try {
            int myflight = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.id(TXT_CORREO)).clear();
            String correo = getData().get(myflight).get(COLUMNA_CORREO);
            driver.findElement(By.name(TXT_CORREO)).sendKeys(correo);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el correo : " + correo);
            generateWord.sendText("Se ingresó el Correo ");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^doy click en el boton crear cuenta$")
    public void doyClickEnElBotonCrearCuenta()  throws Throwable{
        try {
            driver.findElement(By.xpath(BTN_CREARCUENTA)).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón Crear Cuenta");
            generateWord.sendText("Se dió clic en el botón Crear Cuenta ");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @Then("^Se muestra la pantalla de registro$")
    public void seMuestraLaPantallaDeRegistro() {


    }



}
