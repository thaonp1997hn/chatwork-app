package demo.pages;
import demo.common.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountTabPage extends BasePage {

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"setting.cell.mychat\"")
    WebElementFacade btnMyChat;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"tabbar.account\"]")
//    @iOSXCUITFindBy(accessibility = "tabbar.account")
    WebElementFacade btnAccountTab;

    public void clickBtnMyChat(){
        btnMyChat.waitUntilClickable().click();
    }

    public void clickBtnAccountTab(){
        waitUntilElementVisible(10, btnAccountTab);
        btnAccountTab.waitUntilClickable().click();
    }


}
