package demo.common;

import demo.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BasePage extends PageObject {
    private Dimension screenSize;

    public void startApplication() {
    }

    public AppiumDriver<AndroidElement> mobileDriver() {
        return (AppiumDriver<AndroidElement>) ((WebDriverFacade) getDriver()).getProxiedDriver();
    }
    public AppiumDriver<IOSElement> mobileDriverIOS() {
        return (AppiumDriver<IOSElement>) ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    public void scrollDownByAction() {
        if (screenSize == null) {
            screenSize = getDriver().manage().window().getSize();
        }
        int deviceHeight = screenSize.getHeight();
        int deviceWidth = screenSize.getWidth();

        TouchAction action = new TouchAction(mobileDriver());
        action.longPress(PointOption.point(deviceWidth / 2, (deviceHeight / 2) - 100))
                .moveTo(PointOption.point(deviceWidth / 2, 0))
                .release()
                .perform();
    }

    public void scrollDownToWebElementFacade(int index, WebElementFacade e) {
        int i = 0;
        while (!e.isCurrentlyVisible() && i < index) {
            scrollDownByAction();
            i++;
        }
    }

    public void waitUntilElementVisible(int timeoutInSeconds, WebElementFacade e) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                    .pollingEvery(Duration.of(50, ChronoUnit.MILLIS));

            wait.until((ExpectedCondition<Boolean>) d -> {
                try {
                    return e.isVisible();
                } catch (Exception exception) {
                    return false;
                }
            });
        } catch (Exception ex) {
        }
    }

    public void waitUntilElementVisibleByXpath(int timeoutInSeconds, String xpath) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf($(xpath)));
    }

    public void waitUntilXpathInvisible(int timeoutInSeconds, String xpath){
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilElementInvisible(int timeoutInSeconds, WebElementFacade e) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                    .pollingEvery(Duration.of(50, ChronoUnit.MILLIS));

            wait.until((ExpectedCondition<Boolean>) d -> {
                try {
                    return !e.isVisible();
                } catch (Exception exception) {
                    return false;
                }
            });
        } catch (Exception ex) {
        }
    }

    public void waitUntilElementInvisibleByXpath(int timeoutInSeconds, String xpath) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(50, ChronoUnit.MILLIS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilElementInvisibleById(String id) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public void waitUntilElementPresent(int timeoutInSeconds, String x) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(50, ChronoUnit.MILLIS));

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                return findAll(x).size() > 0;
            } catch (Exception exception) {
                return false;
            }
        });
    }

    public void waitElementEnable(int timeoutInSeconds, WebElementFacade e) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(50, ChronoUnit.MILLIS));

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                return e.isEnabled();
            } catch (Exception exception) {
                return false;
            }
        });
    }

    public void tabOnPercentLocation(double xPercent, double yPercent) {
        int heightOfScreen = mobileDriver().manage().window().getSize().getHeight();
        int widthOfScreen = mobileDriver().manage().window().getSize().getWidth();
        TouchAction touchAction = new TouchAction(mobileDriver());
        touchAction
                .tap(PointOption.point((int) (widthOfScreen * xPercent / 100), (int) (heightOfScreen * yPercent / 100)))
                .perform();
    }

    public void tabOnCoordinate(int x, int y) {
        TouchAction touchAction = new TouchAction(mobileDriver());
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    public WebElementFacade getWebElementFacadeByPassTextToXpath(String xpathIOS, String xpathAndroid, String text) {
        String x;
        if (Utilities.isCurrentPlatformAndroid())
            x = xpathAndroid;
        else x = xpathIOS;
        return $(String.format(x, text));
    }

    public WebElementFacade getWebElementFacadeByXpathAndAccessibility(String text) {
        WebElementFacade e;
        if (Utilities.isCurrentPlatformAndroid())
            e = $(String.format("//android.widget.TextView[@text='%s']", text));
        else e = $(MobileBy.AccessibilityId(text));
        return e;
    }
}