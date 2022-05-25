package demo.defs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import demo.steps.ChatBoxSteps;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatBoxDefs {
    @Steps
    ChatBoxSteps chatBoxSteps;

    @Then("^MyChat box is displayed$")
    public void myChatBoxIsDisplayed() {
        chatBoxSteps.verifyMyChatBoxDisplayed();
    }

    @When("^user enters \"([^\"]*)\"$")
    public void userEntersMessage(String mess) {
        chatBoxSteps.enterMessage(mess);
        chatBoxSteps.clickBtnSend();
        chatBoxSteps.waitUntilMessageSent(mess);
        chatBoxSteps.tapOutsideKeyboard();

    }

    @And("^user edits \"([^\"]*)\" to \"([^\"]*)\"$")
    public void userEditsTo(String mess, String editMess) {
        chatBoxSteps.tapsOnMessage(mess);
        chatBoxSteps.chooseFunctionInteractWithMess("Edit");
        chatBoxSteps.enterEditMessage(editMess);
        chatBoxSteps.clickBtnDone();
        chatBoxSteps.waitUntilMessageSent(editMess);
    }

    @When("^user reacts \"([^\"]*)\" to \"([^\"]*)\"$")
    public void reactsTo(String react, String mess) {
        chatBoxSteps.tapsOnMessage(mess);
        chatBoxSteps.chooseFunctionInteractWithMess("Reaction");
        chatBoxSteps.chooseReaction(react);
    }

    @When("^user taps on \"([^\"]*)\"$")
    public void userTapsOn(String mess) {
        chatBoxSteps.tapsOnMessage(mess);
    }

    @When("^user deletes \"([^\"]*)\"$")
    public void userDeletes(String mess) {
        chatBoxSteps.tapsOnMessage(mess);
        chatBoxSteps.chooseFunctionInteractWithMess("Delete");
        chatBoxSteps.clickBtnDelete(mess);
    }

    @Then("^verify all functions interacting with message is shown as below$")
    public void verifyAllFunctionsInteractingWithMessageIsShownAsBelow(DataTable dt) {
        Map<String, String> row = dt.asMaps(String.class, String.class).get(0);
        List<String> listHeader = dt.topCells();
        listHeader.forEach((field) -> {
            String function = row.get(field);
            chatBoxSteps.verifyFunctionInteractWithMessageIsShown(function);
        });
        chatBoxSteps.tapOutsideKeyboard();
    }

    @Then("^verify \"([^\"]*)\" is displayed$")
    public void verifyIsDisplayed(String mess) {
        chatBoxSteps.verifyMessageIsDisplayed(mess);
    }

    @Then("^verify \"([^\"]*)\" is disappeared$")
    public void verifyIsDisappeared(String mess) {
       chatBoxSteps.verifyMessageIsRemoved(mess);
    }

    @Then("^verify reaction attached to \"([^\"]*)\" is displayed$")
    public void verifyReactionAttachedToIsDisplayed(String mess) {
        chatBoxSteps.verifyReactionIsDisplayed(mess);
    }
}
