package classes;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMobile {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestMobile.class.getName());

  protected String language;
  private AppiumDriver driver;
  private URL url;

  /**
   * Metodo para comunicarse con APPIUM y utilizar un dispositivo especifico.
   *
   * @return un boolean indicando si el dispositivo fue levantado correctamente o no
   * @throws Exception si falla el iniciar el dispositivo
   * @author adrian.benedetto
   */
  protected void startDriver() throws Exception {
    try {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities
          .setCapability(MobileCapabilityType.DEVICE_NAME, "GoogleNexus7v5_1_0");
      capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
      url = new URL("http://127.0.0.1:4725/wd/hub");
      capabilities.setCapability(MobileCapabilityType.APP, "/hopper/Hopper-Resources/apk/app-decolar-googlePlay-qa-v6.8.2-SNAPSHOT.apk");
      setDriver(new AndroidDriver(url, capabilities));
      driver.manage().timeouts().implicitlyWait(120000, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      throw new Exception(
          "ERROR: Error al iniciar el dispositivo. Error : " + e);
    }
  }

  /**
   * Este metodo cierra el driver del dispositivo.
   *
   * @throws Exception si falla el cierre del driver
   * @author adrian.benedetto
   */
  protected void closeDriver() throws Exception {
    try {
      Thread.sleep(3000);
      if (driver != null) {
        this.driver.quit();
        Thread.sleep(2000);
      }
      LOGGER.info("Test end.");
    } catch (Exception e) {
      LOGGER.error(
          "ERROR: Hubo un error al cerrar el dispositivo : " + e.getMessage());
      setDriver(null);
    }
    setDriver(null);
  }

  protected AppiumDriver getDriver() {
    return driver;
  }

  public void setDriver(AppiumDriver appiumDriver) {
    driver = appiumDriver;
  }

}
