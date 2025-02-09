import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;

public class Steps {

    @Step("Кликнуть на кнопку поиска")
    void clickOnSearch() {
        $("[data-target='qbsearch-input.inputButtonText']").click();
    }

    @Step("Ввести значение для поиска и нажать Enter")
    void textSearchInput(String value) {
        $("[data-target='query-builder.input']").setValue(value).pressEnter();
    }

    @Step("Открыть репозиторий из результатов поиска")
    void openRepositoryByLink(String value) {
        $(linkText(value)).click();
    }

    @Step("Проверить, что для репозитория есть вкладка Issues")
    void checkEmptyIssuesList() {
        $("#issues-tab").shouldHave(text("Issues"));
    }

}
