package demo.defs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import demo.steps.AccountTabSteps;
import net.thucydides.core.annotations.Steps;

public class AccountTabDefs {
    @Steps
    AccountTabSteps accountTabSteps;

    @Given("^user opens ChatWork app$")
    public void userOpensChatWorkApp() {
    }


    @When("^user chooses Account tab$")
    public void userChoosesAccountTab() {
        accountTabSteps.clickOnAccountTab();
    }

    @And("^user selects MyChat$")
    public void userSelectsMyChat() {
        accountTabSteps.clickOnMyChat();
    }

}
