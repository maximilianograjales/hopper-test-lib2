package classes;

import classes.Constant;
import classes.Page;
import classes.Translator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.remote.MobileCapabilityType;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by it on 12/07/17.
 */
public class MobilePage extends Page {

  public static final String CLOSE_LOGIN_POPUP = "CLOSE_LOGIN_POPUP";
  public static final String USERNAME_FACEBOOK = "diegoqaromero.uy@gmail.com";
  public static final String PASSWORD_FACEBOOK = "DespegarUY2017";
  public static final String CONNECTION_ERROR_TITLE = "CONNECTION_ERROR_TITLE";
  public static final String CONNECTION_ERROR_SUBTITLE = "CONNECTION_ERROR_SUBTITLE";
  public static final String INVALID_EMAIL_FORMAT = "INVALID_EMAIL_FORMAT";
  public static final String INVALID_USERNAME_OR_PASSWORD = "INVALID_USERNAME_OR_PASSWORD";
  public static final String EMPTY_PASSWORD_MESSAGE = "EMPTY_PASSWORD_MESSAGE";
  public static final String OPEN_WITH_RECOVER_PASSWORD_OPTION = "OPEN_WITH_RECOVER_PASSWORD_OPTION";
  public static final String QUICK_ACCESS_OPTION = "QUICK_ACCESS_OPTION";
  public static final String OPEN_WITH_QUICK_ACCESS_OPTION = "OPEN_WITH_QUICK_ACCESS_OPTION";
  public static final String ACTIVATE_ACCOUNT_OPTION = "ACTIVATE_ACCOUNT_OPTION";
  public static final String ACTIVATE_ACCOUNT_OPEN_WITH_OPTION = "ACTIVATE_ACCOUNT_OPEN_WITH_OPTION";
  private static final Logger LOGGER = LoggerFactory
      .getLogger(MobileCapabilityType.class.getName());

  @AndroidFindBy(className = "android.support.v7.widget.LinearLayoutCompat")
  @iOSFindBy(xpath = "//XCUIElementTypeAlert")
  public WebElement errorMessageAndroid;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'alertTitle')]")
  @iOSFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
  public WebElement errorMessageTitleAndroid;

  @AndroidFindBy(id = "android:id/message")
  @iOSFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
  public WebElement errorMessageSubtitleAndroid;

  @AndroidFindBy(id = "android:id/button1")
  @iOSFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeButton")
  public WebElement okButtonInAlert;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'textinput_error')]")
  @iOSFindBy(xpath = "//XCUIElementTypeImage[@name='warning']/../XCUIElementTypeStaticText")
  public WebElement fieldErrorAlert;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'edittext_viewemail_email')]")
  @iOSFindBy(xpath = "//*[@name='mail']/..//XCUIElementTypeTextField")
  public WebElement emailTextField;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'edittext_viewpassword_password')]")
  @iOSFindBy(xpath = "//*[@name='mail']/..//XCUIElementTypeSecureTextField")
  public WebElement passwordTextField;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'imageview_fragmentfinish_success')]")
  @iOSFindBy(xpath = "//XCUIElementTypeImage[@name='check']")
  public WebElement succesfulImage;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'textview_fragmentfinish_title')]")
  @iOSFindBy(xpath = "//XCUIElementTypeImage[@name='check']/../XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
  public WebElement succesfulTitle;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'textview_fragmentfinish_description_1')]")
  @iOSFindBy(xpath = "//XCUIElementTypeImage[@name='check']/../XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
  public WebElement succesfulDescription;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'textview_fragmentfinish_description_2')]")
  @iOSFindBy(xpath = "//XCUIElementTypeImage[@name='check']/../../XCUIElementTypeStaticText")
  public WebElement succesfulLastDescription;

  protected AppiumDriver driver;
  protected Translator translator;
  protected String language;

  @AndroidFindBy(id = "android:id/button_once")
  private WebElement justOnceButton;


  public MobilePage(AppiumDriver mobileDriver, String language) {
    super(mobileDriver);
    this.driver = mobileDriver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    this.language = language;
    setTranslator();
    initElements();
  }

  private void setTranslator() {
    if (translator == null) {
      translator = new Translator(language);
    }
  }

  private void initElements() {
    translator.loadLanguages(CLOSE_LOGIN_POPUP,
        translator.createWordList("Desplazarse hacia arriba", "Navegar para cima"));
    translator.loadLanguages(CONNECTION_ERROR_TITLE, translator
        .createWordList("¡Ups! Parece que no hay Internet", "Ops! Parece que não há Internetn"));
    translator.loadLanguages(CONNECTION_ERROR_SUBTITLE,
        translator.createWordList("Revisa tu conexión y vuelve a intentarlo",
            "Revise sua conexão e tente novamente"));
    translator.loadLanguages(INVALID_EMAIL_FORMAT, translator
        .createWordList("El formato de email no es válido", "O formato do email não é válido"));
    translator.loadLanguages(INVALID_USERNAME_OR_PASSWORD,
        translator.createWordList("El email o contraseña son incorrectos.",
            "El email o contraseña son incorrectos."));
    translator.loadLanguages(EMPTY_PASSWORD_MESSAGE,
        translator.createWordList("Debe ingresar una contraseña", "Deve inserir uma senha"));
    translator.loadLanguages(OPEN_WITH_RECOVER_PASSWORD_OPTION,
        translator.createWordList("Open with Recuperar cuenta", "Open with Recuperar conta"));
    translator.loadLanguages(QUICK_ACCESS_OPTION,
        translator.createWordList("Inicio rápido", "Acesso rápido"));
    translator.loadLanguages(OPEN_WITH_QUICK_ACCESS_OPTION,
        translator.createWordList("Open with Inicio rápido", "Open with Acesso rápido"));
    translator.loadLanguages(ACTIVATE_ACCOUNT_OPTION,
        translator.createWordList("Confirmar cuenta", "Confirmar sua conta"));
    translator.loadLanguages(ACTIVATE_ACCOUNT_OPEN_WITH_OPTION,
        translator.createWordList("Open with Confirmar cuenta", "Open with Confirmar sua conta"));
  }

  /**
   * Metodo que cierra un popup (ej: popup que se levanta para loguearse).
   *
   * @throws Exception si existe un error al cerrar el popup
   * @author maximiliano.grajales
   */
  public void closePopUp() throws Exception {
    if (isElementPresentShortest(getCloseLoginPopupXpath())) {
      waitForElementPresentLong(getClosePopupElement());
      tap(getClosePopupElement());
    }
  }


  /**
   * Metodo que va hacia atras en el dispositivo.
   *
   * @author maximiliano.grajales
   */
  public void goBack() {
    driver.navigate().back();
  }

  /**
   * Metodo que va hacia atras en el dispositivo N cantidad de veces.
   *
   * @param times entero con la cantidad de veces que se quiere ejecutar el ir hacia atras
   * @author maximiliano.grajales
   */
  public void goBackTimes(int times) {
    for (int i = 0; i < times; i++) {
      driver.navigate().back();
    }
  }


  /**
   * Metodo que hace un swipe hacia abajo.
   *
   * @author maximiliano.grajales
   */
  public void scrollDown() {
    Dimension size = driver.manage().window().getSize();
    int endy = (int) (size.width * 0.40);
    scrollTo(endy);
  }

  /**
   * Metodo que espera a un elemento  por 15 Segundos y hace un tap (click) en el mismo.
   *
   * @param element elemento
   * @throws Exception falla si hay un error al clickear o al buscar el elemento
   */
  public void tap(WebElement element) throws Exception {
    try {
      waitForElementPresentLong(element);
      element.click();
    } catch (Exception ex) {
      LOGGER.error("Error al clickear en elemento! " + ex);
      throw new Exception(ex);
    }
  }

  /**
   * Metodo que espera 3 Segundos el elemento para realizar el tap.
   *
   * @param element {@link WebElement} en que se va a realizar el tap
   * @throws Exception si falla al hacer el tap
   * @author Carlos Torres
   */
  public void shortTap(WebElement element) throws Exception {
    waitForElementPresentShort(element);
    element.click();
  }


  /**
   * Metodo que hace swipe hacia abajo N cantidad de veces.
   *
   * @param times entero con la cantidad de veces
   * @author maximiliano.grajales
   */
  public void scrollDownTimes(int times) {
    for (int i = 0; i < times; i++) {
      scrollDown();
    }
  }

  public WebElement getClosePopupElement() {
    return driver.findElement(By.xpath(getCloseLoginPopupXpath()));
  }

  public boolean isAnErrorPopupMessageDisplayed() throws Exception {
    waitForElementPresentLong(errorMessageAndroid);
    return errorMessageAndroid.isDisplayed();
  }

  public String getAlertTitleMessage() throws Exception {
    waitForElementPresentLong(errorMessageTitleAndroid);
    return errorMessageTitleAndroid.getText();
  }

  public String getAlertSubtitleMessage() throws Exception {
    waitForElementPresentLong(errorMessageSubtitleAndroid);
    return errorMessageSubtitleAndroid.getText();
  }

  /**
   * Metodo que clickea en "Ok" en alguna alerta que despliegue Android.
   *
   * @throws Exception si falla al hacer tap en el elemento
   * @author maximiliano.grajales
   */
  public void tapOnOkAlert() throws Exception {
    tap(okButtonInAlert);
  }

  public boolean isAnErrorFieldMessageDisplayed() throws Exception {
    waitForElementPresentLong(fieldErrorAlert);
    return fieldErrorAlert.isDisplayed();
  }

  public String getFieldErrorMessage() throws Exception {
    waitForElementPresentLong(fieldErrorAlert);
    return fieldErrorAlert.getText();
  }

  public void enterEmailAccount(String email) {
    emailTextField.sendKeys(email);
  }

  public void enterPassword(String password) {
    passwordTextField.sendKeys(password);
  }

  /**
   * Este metodo verifica si se muestra un mensaje de operacion finalizada con exito. (tick verde).
   *
   * @return un booleano indicando si mensaje se muestra correctamente o no.
   * @throws Exception si existe un error con los elementos al esperarlos.
   * @author maximiliano.grajales
   */
  public boolean wasProcessSuccesful() throws Exception {
    waitForElementPresentLong(succesfulTitle);
    return succesfulImage.isEnabled() && succesfulDescription.isDisplayed() && succesfulTitle
        .isDisplayed() && succesfulLastDescription.isEnabled();
  }

  /**
   * Esconde el teclado de mobile.
   *
   * @throws Exception si falla al hacer escondel el teclado
   * @author Emmanuel Kurpas
   */
  public void hideKeyboard() {
    try {
      driver.hideKeyboard();
    } catch (Exception ex) {
      LOGGER.info("no se pudo ocultar el keyboard");
    }
  }

  /**
   * Metodo que devuelve el WebElement que se despliega en el popup de mobile cuando se clickea en
   * "Recuperar contraseña". Puede suceder que el popup de mobile muestre opciones del tipo :
   * "Decolar", "Despegar" como tambien puede suceder que la opcion sea : "Open with Decolar" "Open
   * with Despegar".
   *
   * @param firstOption primera opcion que podria aparecer, pasar la constante. Ejemplo :
   * QUICK_ACCESS_OPTION
   * @param secondOption segunda opcion que podria aparecer, pasar la constante. Ejemplo :
   * OPEN_WITH_QUICK_ACCESS_OPTION
   * @return devuelve el webelement para ser clickeado
   * @throws Exception si falla al encontrar ambos elementos
   * @author maximiliano.grajales
   */
  public WebElement getOpenWithElement(String firstOption, String secondOption) throws Exception {
    try {
      waitForElementPresentShort(translator.trXpath(firstOption));
      return driver.findElement(By.xpath(translator.trXpath(firstOption)));
    } catch (Exception ex) {
      waitForElementPresentShort(translator.trXpath(secondOption));
      return driver.findElement(By.xpath(translator.trXpath(secondOption)));
    }
  }

  /**
   * Metodo privado para que seleccione en el popup de Android con que aplicacion quiere abrir el
   * link.
   *
   * @param firstOption primera opcion que podria aparecer, pasar la constante. Ejemplo :
   * QUICK_ACCESS_OPTION
   * @param secondOption segunda opcion que podria aparecer, pasar la constante. Ejemplo :
   * OPEN_WITH_QUICK_ACCESS_OPTION
   * @throws Exception si falla al seleccionar la opcion
   * @author maximiliano.grajales
   */
  protected void openWithOptionAndroid(String firstOption, String secondOption) throws Exception {
    waitForElementPresentLong(getOpenWithElement(firstOption, secondOption));
    tap(getOpenWithElement(firstOption, secondOption));
    tap(justOnceButton);
  }

  /**
   * Mantiene el Tap al elemento por mas tiempo para desplegar las opciones.
   *
   * @param element al que se le realiza la acción
   * @throws Exception si falla al hacer tap en el elemento
   * @author Carlos Torres
   */
  public void tapLongTime(WebElement element) throws Exception {
    waitForElementPresentShort(element);
    TouchAction action = new TouchAction(driver);
    action.longPress(element).release().perform();
  }

  /**
   * Realiza un Scroll en la pantalla sobre un elemento en especifico, Recomendable sobre elementos
   * que no sean clickeables. Con esto se evita que al realizar un scroll se haga un tap en un
   * elemento sin querer.
   *
   * @param element Elemento sobre donde se quiere realizar el scroll a la pantalla
   * @param positions cantidad de posiciones que va a realizar el scroll
   * @param direction Indica la dirección que se desea realizar el scroll <b>DOWN</b> ó <b>UP</b>
   * @author Carlos Torres
   */
  public void scrollInElement(WebElement element, int positions, String direction) {
    if (positions > 0) {
      int x = element.getLocation().getX() + (element.getSize().getWidth() / 2);
      int y = element.getLocation().getY();
      if (direction.equalsIgnoreCase("down")) {
        y -= 200;
      } else if (direction.equalsIgnoreCase("up")) {
        y += 200;
      }

      TouchAction action = new TouchAction(driver);
      for (int i = 0; i < positions; i++) {
        action.longPress(element).moveTo(x, y).release().perform();
      }
    }
  }

  /**
   * se le pasan 2 strings, el 1ero la password q ingrese y el 2do la password q tome de algun campo
   * con los caracteres ocultos, lo q hace es corroborar el length de cada uno y comparar el pass
   * con caracteres ocultos con un string armado.
   *
   * @param passNow string
   * @param passRow string
   * @return boolean
   * @author Emmanuel Kuroas
   */
  public boolean isPassNotVisible(String passNow, String passRow) {
    int length = passRow.length();
    String notShow = "";
    for (int i = 0; i < length; i++) {
      notShow = notShow.concat("•");
    }
    if (passNow.length() == length && notShow.equalsIgnoreCase(passRow)) {
      return true;
    }
    return false;
  }


  public boolean elementExists(WebElement element) {
    try {
      changeImplicitlyWait(driver, 1000);
      boolean isDisplayed = element.isDisplayed();
      restoreImplicitlyWait(driver);
      return isDisplayed;
    } catch (Exception ex) {
      return false;
    }
  }


  /**
   * Realiza un swipe down a un Elemento indicando cuantas posiciones debe moverse.
   *
   * @param element WebElement al que se le realiza el swipe
   * @param positions numero de posiciones a mover el elemento
   * @author Carlos Torres
   */
  public void swipeElement(WebElement element, int positions) {
    if (positions > 0) {
      int x = element.getLocation().getX() + (element.getSize().getWidth() / 2);
      int y = element.getLocation().getY() + (element.getSize().getHeight() / 4);
      TouchAction action = new TouchAction(driver);
      for (int i = 0; i < positions; i++) {
        action.longPress(element).moveTo(x, y).release().perform();
      }
    }
  }

  public Translator getTranslator() {
    return translator;
  }


  public String getCloseLoginPopupXpath() {
    return "//*[@content-desc='" + translator.tr(CLOSE_LOGIN_POPUP) + "']";
  }

  /**
   * Metodo para mover el driver al contexto web.
   *
   * @author maximiliano.grajales
   */
  public void switchToWebViewContext() {
    Set<String> contextNames = driver.getContextHandles();
    for (String contextName : contextNames) {
      if (contextName.contains("WEBVIEW")) {
        driver.context(contextName);
      }
    }
  }

  /**
   * Metodo para mover el driver al contexto nativo.
   *
   * @author maximiliano.grajales
   */
  public void switchToNativeContext() {
    driver.context("NATIVE_APP");
  }

  /**
   * Metodo para hacer scroll a un elemento.
   *
   * @param element elemento
   * @author maximiliano.grajales
   */
  public void scrollToElement(WebElement element) {
    scrollTo(element.getLocation().getY() / 6);
    LOGGER.info("Scroll al objeto finalizado!");
  }

  /**
   * Metodo para hacer scroll en la pantalla dado una Y.
   *
   * @param y coordenadas
   * @author maximiliano.grajales
   */
  private void scrollTo(int y) {
    Dimension size = driver.manage().window().getSize();
    int starty = (int) (size.height * 0.90);
    TouchAction action = new TouchAction(driver);
    action.longPress(0, starty).moveTo(0, y).release().perform();
  }

  /**
   * Metodo que hace un swipe hacia arriba.
   *
   * @author maximiliano.grajales
   */
  public void scrollUp() {
    Dimension size = driver.manage().window().getSize();
    int startx = size.width / 2;
    int starty = (int) (size.height * 0.80);
    int endy = (int) (size.width * 0.20);
    TouchAction action = new TouchAction(driver);
    action.longPress(startx, endy).moveTo(startx, starty).release().perform();
  }

  /**
   * Metodo que abre y cierra las notificaciones de Android.
   *
   * @throws InterruptedException si falla el sleep
   * @author maximiliano.grajales
   */
  public void openAndCloseNotifications() throws InterruptedException {
    ((AndroidDriver) driver).openNotifications();
    Thread.sleep(Constant.TIMEOUT_MIL_2000);
    driver.navigate().back();
  }
}
