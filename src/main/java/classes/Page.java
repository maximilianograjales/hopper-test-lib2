package classes;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Super Class para Metodos Comunes en Web como en Mobile.
 *
 * @author Carlos Torres
 */
public class Page {

  private static final Logger LOGGER = LoggerFactory.getLogger(Page.class.getName());

  protected WebDriverWait internalWait;
  protected WebDriverWait shortInternalWait;
  protected WebDriverWait shortestInternalWait;
  protected WebDriverWait longInternalWait;
  private WebDriver driver;


  public Page(WebDriver driver) {
    this.internalWait = new WebDriverWait(driver, Constant.TIMEOUT_SEG_120);
    this.shortInternalWait = new WebDriverWait(driver, Constant.TIMEOUT_SEG_5);
    this.shortestInternalWait = new WebDriverWait(driver, Constant.TIMEOUT_SEG_1);
    this.longInternalWait = new WebDriverWait(driver, Constant.TIMEOUT_SEG_15);
    this.driver = driver;
  }


  /**
   * Metodo Privado que espera por un Elemento.
   *
   * @param element {@link WebElement} al que debe esperar
   * @param wait Tiempo que debe esperar
   * @throws Exception Si falla al no encontrar el elemento en el tiempo indicado
   * @author Carlos Torres
   */
  private void waitForElementPresent(WebElement element, WebDriverWait wait, int time)
      throws Exception {
    changeImplicitlyWait(driver, time);
    wait.until(ExpectedConditions.visibilityOf(element));
    restoreImplicitlyWait(driver);
  }

  /**
   * Espera que el elemento este Presente durante 3 Segundos.
   *
   * @param element {@link WebElement} al que debe esperar
   * @throws Exception al no encontrar el elemento pasado el tiempo
   * @author Carlos Torres
   */
  public void waitForElementPresentShort(WebElement element) throws Exception {
    waitForElementPresent(element, shortInternalWait, Constant.TIMEOUT_MIL_5000);
  }


  /**
   * Espera que el elemento este Presente durante 15 Segundos.
   *
   * @param element {@link WebElement} al que debe esperar
   * @throws Exception al no encontrar el elemento pasado el tiempo
   * @author Carlos Torres
   */
  public void waitForElementPresentLong(WebElement element) throws Exception {
    waitForElementPresent(element, longInternalWait, Constant.TIMEOUT_MIL_120000);
  }

  /**
   * Metodo que espera a que el elemento se encuentre presente en un tiempo de 1 seg.
   *
   * @param element webelement
   * @throws Exception falla si el elemento es null
   * @author maximiliano.grajales
   */
  public void waitForElementPresentShortest(WebElement element) throws Exception {
    waitForElementPresent(element, shortestInternalWait, Constant.TIMEOUT_MIL_1000);
  }

  /**
   * Metodo privado que espera que un elemento sea visible pero buscando por XPATH, este metodo se
   * debe usar cuando el elemento que queremos saber si esta visible lo ubicamos usando
   * driver.findelement.
   *
   * @param element elemento
   * @param wait tiempo de espera
   * @throws Exception si falla al no encontrar el elemento
   * @author maximiliano.grajales
   */
  private void waitForElementPresent(String element, WebDriverWait wait, int time)
      throws Exception {
    changeImplicitlyWait(driver, time);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    restoreImplicitlyWait(driver);
  }


  /**
   * Metodo que espera a que el elemento se encuentre presente en el dispositivo pero buscando por
   * XPATH, este metodo se debe usar cuando el elemento que queremos saber si esta visible lo
   * ubicamos usando driver.findelement.
   *
   * @param element webelement
   * @throws Exception falla si el elemento es null
   * @author maximiliano.grajales
   * @author maximiliano.grajales
   */
  public void waitForElementPresentLong(String element) throws Exception {
    waitForElementPresent(element, internalWait, Constant.TIMEOUT_MIL_120000);
  }

  /**
   * Metodo que espera en un tiempo corto (5 segundos) la aparicion de un elemento pero buscando por
   * XPATH, este metodo se debe usar cuando el elemento que queremos saber si esta visible lo
   * ubicamos usando driver.findelement.
   *
   * @param element elemento
   * @throws Exception si falla al encontrar el elemento
   * @author maximiliano.grajales
   */
  public void waitForElementPresentShort(String element) throws Exception {
    waitForElementPresent(element, shortInternalWait, Constant.TIMEOUT_MIL_5000);
  }


  /**
   * Valida si el Elemento esta presente en la pagina.
   *
   * @param element {@link WebElement} que busca.
   * @return {@link Boolean} <b>True</b> Si encuentra el elemento en la pagina, <b>False</b> en caso
   * contrario.
   * @author Carlos Torres
   */
  public boolean isElementPresent(WebElement element) {
    try {
      waitForElementPresentShort(element);
      return element.getSize().height > 0 || element.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Metodo que espera a que un elemento no se encuentre visible.
   *
   * @param element WebElement
   * @throws Exception falla si no lo encuentra
   * @author maximiliano.grajales
   */
  public void waitForElementDissapears(WebElement element) throws Exception {
    internalWait.until(ExpectedConditions.invisibilityOf(element));
  }

  /**
   * Valida si el Elemento esta presente en la pagina pasandole el XPATH.
   *
   * @param driver webdriver
   * @param xpath xpath del elemento
   * @return {@link Boolean} <b>True</b> Si encuentra el elemento en la pagina, <b>False</b> en caso
   * contrario.
   * @author Maximiliano Piñeyro
   */
  public boolean isElementPresent(WebDriver driver, String xpath) {
    try {
      changeImplicitlyWait(driver, Constant.TIMEOUT_MIL_5000);
      WebElement element = driver.findElement(By.xpath(xpath));
      restoreImplicitlyWait(driver);
      return element.getSize().height > 0;
    } catch (Exception e) {
      return false;
    }
  }


  public void changeImplicitlyWait(WebDriver driver, int timeout) {
    driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
  }

  public void restoreImplicitlyWait(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(Constant.TIMEOUT_MIL_120000, TimeUnit.MILLISECONDS);
  }

  /**
   * Valida si el Elemento esta presente en la pagina pasandole el elemento.
   *
   * @param element elemento a encontrar
   * @return {@link Boolean} <b>True</b> Si encuentra el elemento en la pagina, <b>False</b> en caso
   * contrario.
   * @author Maximiliano Piñeyro
   */
  public boolean isElementPresentShortest(WebElement element) {
    try {
      waitForElementPresentShortest(element);
      return element.getSize().height > 0;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean isElementClickable(WebElement element) {
    try {
      changeImplicitlyWait(driver, Constant.TIMEOUT_MIL_2000);
      shortInternalWait.until(ExpectedConditions.elementToBeClickable(element));
      restoreImplicitlyWait(driver);
      LOGGER.info("Elemento es clickeable");
      return true;
    } catch (Exception ex) {
      restoreImplicitlyWait(driver);
      LOGGER.info("Elemento no es clickeable");
      return false;
    }
  }

  /**
   * Valida si el Elemento esta presente en la pagina pasandole el XPATH.
   *
   * @param xpath elemento a encontrar
   * @return {@link Boolean} <b>True</b> Si encuentra el elemento en la pagina, <b>False</b> en caso
   * contrario.
   * @author Maximiliano Piñeyro
   */
  public boolean isElementPresentShortest(String xpath) {
    try {
      changeImplicitlyWait(driver, Constant.TIMEOUT_MIL_1000);
      WebElement element = driver.findElement(By.xpath(xpath));
      restoreImplicitlyWait(driver);
      return element.getSize().height > 0;
    } catch (Exception e) {
      return false;
    }
  }
}
