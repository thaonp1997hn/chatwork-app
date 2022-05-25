package demo.pages;

import demo.common.BasePage;
import demo.utils.Utilities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ChatBoxPage extends BasePage {
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Enter your message here']//preceding-sibling::XCUIElementTypeTextView")
    @AndroidFindBy(id = "timeline_input_edit_text")
    WebElementFacade txtEnterMess;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Editing Message')]/following::XCUIElementTypeTextView[1]")
    @AndroidFindBy(id = "timeline_input_edit_text")
    WebElementFacade txtEditMess;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Send']")
    @AndroidFindBy(id = "timeline_input_select_send")
    WebElementFacade btnSend;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    @AndroidFindBy(id = "timeline_input_select_send")
    WebElementFacade btnDone;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='My Chat']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Chat']")
    WebElementFacade lblMyChat;
    @iOSXCUITFindBy(accessibility = "Delete")
    @AndroidFindBy(id = "android:id/button1")
    WebElementFacade btnDelete;

    public void clickBtnDone() {
        btnDone.waitUntilClickable().click();
    }

    public void clickBtnSend() {
        btnSend.waitUntilClickable().click();
    }

    public void enterMessage(String mess) {
        txtEnterMess.waitUntilPresent().sendKeys(mess);
    }

    public void enterEditMessage(String editMess) {
        txtEditMess.waitUntilPresent().clear();
        txtEditMess.sendKeys(editMess);
    }

    public boolean isMyChatBoxDisplayed() {
        return lblMyChat.isPresent();
    }

    public void waitUntilMessageSent(String mess) {
        String xpathIcSend;
        if (!Utilities.isCurrentPlatformAndroid()) {
            xpathIcSend = "//XCUIElementTypeTextView[@name='" + mess + "']/../following-sibling::XCUIElementTypeImage[@name='ico-send']";
            waitUntilXpathInvisible(20, xpathIcSend);
        }
    }

    public void tapOutsideKeyboard() {
        tabOnPercentLocation(50, 20);
    }

    public void tapsOnMessage(String mess) {
        String xpathIOS = "//XCUIElementTypeTextView[@name='%s' and @enabled='true']";
        String xpathAndroid = "//android.widget.TextView[@text='%s']";
        WebElementFacade e = getWebElementFacadeByPassTextToXpath(xpathIOS, xpathAndroid, mess);
        e.waitUntilClickable().click();
    }

    public void chooseFunctionInteractWithMess(String function) {
        getWebElementFacadeByXpathAndAccessibility(function).click();
    }

    public void chooseReact(String react) {
        getWebElementFacadeByXpathAndAccessibility(react).click();
    }

    public void clickBtnDelete(String mess) {
        btnDelete.waitUntilClickable().click();
        String xpath;
        if (Utilities.isCurrentPlatformAndroid())
            xpath = "//android.widget.TextView[@text='" + mess + "']";
        else xpath = "//XCUIElementTypeTextView[@name='" + mess + "']";
        waitUntilXpathInvisible(10, xpath);
    }

    public boolean isFunctionInteractWithMessShown(String function) {
        return getWebElementFacadeByXpathAndAccessibility(function).isVisible();
    }

    public boolean isMessageDisplayed(String mess) {
        String xpath;
        if (Utilities.isCurrentPlatformAndroid())
            xpath = "//android.widget.TextView[@text='" + mess + "']";
        else xpath = "//XCUIElementTypeTextView[@name='" + mess + "']";
        return $(xpath).isVisible();
    }

    public boolean isReactionDisplayed(String mess) {
        String xpathIOS = "//XCUIElementTypeTextView[@name='" + mess + "']/../preceding-sibling::XCUIElementTypeStaticText[@name='1']";
        String xpathAndroid = "//android.widget.TextView[@text='" + mess + "']/../../following-sibling::android.view.ViewGroup[contains(@resource-id,'reaction')]";
        WebElementFacade e = getWebElementFacadeByPassTextToXpath(xpathIOS, xpathAndroid, mess);
        return e.isVisible();
    }

}
