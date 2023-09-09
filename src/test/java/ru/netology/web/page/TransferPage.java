package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import javax.xml.crypto.Data;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement transferButton = $("[data-test-id = 'action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id = 'amount'] input");
    private final SelenideElement fromInput = $("[data-test-id = 'from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id = 'error-message'] input");

    public TransferPage(){
        transferHead.shouldBe(visible);
    }
    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardinfo){
        makeTransfer(amountToTransfer, cardinfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText){
        errorMessage.shouldHave(Condition.exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
