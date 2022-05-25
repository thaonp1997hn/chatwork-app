package demo.steps;

import demo.pages.ChatBoxPage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChatBoxSteps {
    ChatBoxPage onPage;

    public void verifyMyChatBoxDisplayed(){
        assertThat(onPage.isMyChatBoxDisplayed()).isTrue();
    }

    public void enterMessage(String mess){
        onPage.enterMessage(mess);
    }

    public void tapsOnMessage(String mess){
        onPage.tapsOnMessage(mess);
    }

    public void enterEditMessage(String editMess){
        onPage.enterEditMessage(editMess);
    }

    public void chooseFunctionInteractWithMess(String function){
        onPage.chooseFunctionInteractWithMess(function);
    }

    public void chooseReaction(String react){
        onPage.chooseReact(react);
    }

    public void clickBtnDelete(String mess){
        onPage.clickBtnDelete(mess);
    }

    public void verifyFunctionInteractWithMessageIsShown(String function){
        assertThat(onPage.isFunctionInteractWithMessShown(function)).isTrue();
    }

    public void verifyMessageIsDisplayed(String mess){
        assertThat(onPage.isMessageDisplayed(mess)).isTrue();
    }

    public void verifyMessageIsRemoved(String mess){
        assertThat(onPage.isMessageDisplayed(mess)).isFalse();
    }

    public void clickBtnDone(){
        onPage.clickBtnDone();
    }

    public void clickBtnSend(){
        onPage.clickBtnSend();
    }

    public void waitUntilMessageSent(String mess){
        onPage.waitUntilMessageSent(mess);
    }

    public void tapOutsideKeyboard(){
        onPage.tapOutsideKeyboard();
    }

    public void verifyReactionIsDisplayed(String mess){
        onPage.isReactionDisplayed(mess);
    }
}
