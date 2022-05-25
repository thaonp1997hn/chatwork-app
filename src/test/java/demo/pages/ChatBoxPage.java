package demo.pages;

import demo.common.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ChatBoxPage extends BasePage {
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Enter your message here']//preceding-sibling::XCUIElementTypeTextView")
    WebElementFacade txtEnterMess;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Editing Message')]/following::XCUIElementTypeTextView[1]")
    WebElementFacade txtEditMess;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Send']")
    WebElementFacade btnSend;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    WebElementFacade btnDone;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='My Chat']")
    WebElementFacade lblMyChat;
    @iOSXCUITFindBy(accessibility = "Delete")
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
        String xpathIcSend = "//XCUIElementTypeTextView[@name='" + mess + "']/../following-sibling::XCUIElementTypeImage[@name='ico-send']";
        waitUntilXpathInvisible(20, xpathIcSend);
    }

    public void tapOutsideKeyboard(){
        tabOnPercentLocation(50, 20);
    }

    public void tapsOnMessage(String mess) {
        String xpath = "//XCUIElementTypeTextView[@name='%s' and @enabled='true']";
        String xpathFormat = String.format(xpath, mess);
        $(xpathFormat).waitUntilClickable().click();
    }

    public void chooseFunctionInteractWithMess(String function) {
        $(MobileBy.AccessibilityId(function)).click();
    }

    public void chooseReact(String react) {
        $(MobileBy.AccessibilityId(react)).click();
    }

    public void clickBtnDelete(String mess) {
        btnDelete.waitUntilClickable().click();
        String xpath = "//XCUIElementTypeTextView[@name='" + mess + "']";
        waitUntilXpathInvisible(5, xpath);
    }

    public boolean isFunctionInteractWithMessShown(String function) {
        return $(MobileBy.AccessibilityId(function)).isVisible();
    }

    public boolean isMessageDisplayed(String mess) {
        String xpath = "//XCUIElementTypeTextView[@name='" + mess + "']";
        return $(xpath).isVisible();
    }

    public boolean isReactionDisplayed(String mess){
        String xpath = "//XCUIElementTypeTextView[@name='" + mess + "']/../preceding-sibling::XCUIElementTypeStaticText[@name='1']";
        return $(xpath).isVisible();
    }

}
