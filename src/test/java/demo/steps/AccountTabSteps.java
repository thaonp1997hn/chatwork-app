package demo.steps;

import demo.pages.AccountTabPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountTabSteps {
    AccountTabPage onPage;

    public void clickOnMyChat(){
        onPage.clickBtnMyChat();
    }

    public void clickOnAccountTab(){
        onPage.clickBtnAccountTab();
    }
}
