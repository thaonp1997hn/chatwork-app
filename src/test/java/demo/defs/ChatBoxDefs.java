package demo.defs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import demo.steps.ChatBoxSteps;
import demo.utils.Utilities;
import net.thucydides.core.annotations.Steps;

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
        if (!Utilities.isCurrentPlatformAndroid())
            chatBoxSteps.tapOutsideKeyboard();
    }

    @When("^user taps on \"([^\"]*)\"$")
    public void userTapsOn(String mess) {
        chatBoxSteps.tapsOnMessage(mess);
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

    @When("^user chooses function as below$")
    public void userChoosesFunctionAsBelow(DataTable dt) {
        Map<String, String> row = dt.asMaps(String.class, String.class).get(0);
        String function = row.get("function");
        String mess = row.get("message");
        chatBoxSteps.tapsOnMessage(mess);
        switch (function) {
            case "Reaction":
            case "Choose Reaction":
                chatBoxSteps.chooseFunctionInteractWithMess(function);
                chatBoxSteps.chooseReaction(row.get("icon"));
                break;
            case "Edit":
                String editMess = row.get("edit message");
                chatBoxSteps.chooseFunctionInteractWithMess(function);
                chatBoxSteps.enterEditMessage(editMess);
                chatBoxSteps.clickBtnDone();
                chatBoxSteps.waitUntilMessageSent(editMess);
                break;
            case "Delete":
                chatBoxSteps.chooseFunctionInteractWithMess(function);
                chatBoxSteps.clickBtnDelete(mess);
                break;
            default:
                break;

        }
    }
}
