package com.tsoft.bot.frontend.steps.mimovistar;

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

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import static com.tsoft.bot.frontend.pageobject.mimovistar.PageObject_Recargas.*;
import static org.openqa.selenium.By.*;

public class MiMovistar_Recargas {

    private static final String EXCEL_WEB = "excel/MiMovistar_Recargas.xlsx";
    private static final String RECARGAS_WEB = "Recargas";
    private static final String COLUMNA_URL = "URL";
    private static final String COLUMNA_TELEFONO = "TELEFONO";
    private static final String COLUMNA_MONTO = "MONTO";
    private static final String COLUMNA_CORREO = "CORREO";
    private static final String COLUMNA_NUMTARJETA = "NUM_TARJETA";
    private static final String COLUMNA_FV_MES = "FV_MES";
    private static final String COLUMNA_FV_ANIO = "FV_ANIO";
    private static final String COLUMNA_COD_CVV = "COD_CVV";
    private static GenerateWord generateWord = new GenerateWord();
    private WebDriver driver;

    public MiMovistar_Recargas() {
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, RECARGAS_WEB);
    }

    @Given("^se ingresa en la URL el token generado \"([^\"]*)\"$")
    public void seIngresaEnLaURLElTokenGenerado(String casoDePrueba) throws Throwable {
        try {
            int recargas = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(recargas).get(COLUMNA_URL);
            driver.get(url);
            Sleeper.Sleep(3500);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se inició correctamente la página Mi Movistar con el token");
            generateWord.sendText("Se inició correctamente la página Mi Movistar con el token");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @And("^se ingresa el numero celular \"([^\"]*)\"$")
    public void seIngresaElNumeroCelular(String casoDePrueba) throws Throwable {
        try {
            int recargas = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.id(TXT_NUMERO_MOVISTAR)).clear();
            String telefono = getData().get(recargas).get(COLUMNA_TELEFONO);
            driver.findElement(By.id(TXT_NUMERO_MOVISTAR)).sendKeys(telefono);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el telefono : " + telefono);
            generateWord.sendText("Se ingresó el telefono ");
            generateWord.addImageToWord(driver);

        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }

    }

    @And("^monto a recargar \"([^\"]*)\"$")
    public void montoARecargar(String casoDePrueba) throws Throwable {
        try {
            int recargas = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.id(TXT_MONTO)).clear();
            String monto = getData().get(recargas).get(COLUMNA_MONTO);
            driver.findElement(By.id(TXT_MONTO)).sendKeys(monto);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el monto en soles : " + monto);
            generateWord.sendText("Se ingresó el monto en soles ");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }

    }

    @And("^se da click en el boton Continuar$")
    public void seDaClickEnElBotonContinuar() throws Throwable {
        try {
            driver.findElement(By.xpath(BTN_CONTINUAR)).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón Contiuar");
            generateWord.sendText("Se dió clic en el botón Continuar ");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }


    @When("^se ingresa el email \"([^\"]*)\" y se da click en continuar$")
    public void seIngresaElEmailYSeDaClickEnContinuar(String casoDePrueba) throws Throwable {
        try {
            int recargas = Integer.parseInt(casoDePrueba) - 1;
            String correo = getData().get(recargas).get(COLUMNA_CORREO);
            Thread.sleep(10000);
            driver.findElement(xpath("/html/body")).click();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(2000);

            String text = correo;
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(5000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresa el correo: " + text);
            generateWord.sendText("Se ingresa el correo: " + text);
            generateWord.addImageToWord(driver);
//           String a="mycorreo";
//            char c;
//            int d=a.length(),e=0,f=0;
//            while(e<=d)
//            {
//                c=a.charAt(e);
//                f=(int) c; //converts character to Unicode.
//                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(f));
//                e++;
//                Thread.sleep(150);
//            }
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón Contiuar2");
            generateWord.sendText("Se dió clic en el botón Continuar 2");
            generateWord.addImageToWord(driver);


        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }

    }

    @And("^se ingresa el numero de tarjeta \"([^\"]*)\"$")
    public void seIngresaElNumeroDeTarjeta(String casoDePrueba) throws Throwable {
        try {
            Robot robot = new Robot();
            Thread.sleep(15000);
            robot.keyPress(KeyEvent.VK_4);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyPress(KeyEvent.VK_4);
            robot.keyPress(KeyEvent.VK_8);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyPress(KeyEvent.VK_0);
            robot.keyPress(KeyEvent.VK_7);
            robot.keyPress(KeyEvent.VK_8);
            robot.keyPress(KeyEvent.VK_5);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyPress(KeyEvent.VK_0);
            robot.keyPress(KeyEvent.VK_6);
            robot.keyPress(KeyEvent.VK_7);
            robot.keyPress(KeyEvent.VK_TAB);

            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó la tarjeta de crédito : 4919148107859067");
            generateWord.sendText("Se ingresó la tarjeta de crédito : 4919148107859067");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @And("^se ingresa el mes de vencimiento \"([^\"]*)\"$")
    public void seIngresaElMesDeVencimiento(String arg0) throws Throwable {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_TAB);

            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el mes: 03");
            generateWord.sendText("Se ingresó el mes : 03");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @And("^se ingresa el anio de vencimiento \"([^\"]*)\"$")
    public void seIngresaElAnioDeVencimiento(String arg0) throws Throwable {
        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_DOWN);

            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó el año: 2020");
            generateWord.sendText("Se ingresó el año: 2020");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @And("^se ingresa el codigo de verificacion \"([^\"]*)\"$")
    public void seIngresaElCodigoDeVerificacion(String arg0) throws Throwable {
        try{
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);

            robot.keyPress(KeyEvent.VK_7);
            robot.keyPress(KeyEvent.VK_4);
            robot.keyPress(KeyEvent.VK_0);
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_TAB);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó EL CÓDIGO CVV: 740");
            generateWord.sendText("Se ingresó EL CÓDIGO CVV: 740");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @And("^se da click en el boton Pagar$")
    public void seDaClickEnElBotonPagar() throws Throwable {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(5000);
        } catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }

    @Then("^se verifica que se hizo la recarga correcta\\.$")
    public void seVerificaQueSeHizoLaRecargaCorrecta() throws Exception {
        Thread.sleep(15000);
        if (driver.findElement(By.xpath(LBL_EXITO))!=null){
            System.out.println("Diferente de null");
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "PASS");
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se finalizó correctamente el flujo");
            generateWord.sendText("Se finalizó correctamente el flujo");
            generateWord.addImageToWord(driver);
        }else{
            System.out.println("IGUAL A NULL");
            ExcelReader.writeCellValue(EXCEL_WEB, RECARGAS_WEB, 1, 10, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Falló el Flujo de Recarga");
            generateWord.sendText("Falló el Flujo de Recarga");
            generateWord.addImageToWord(driver);
        }
    }
}