import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureTests extends TestBase {

    Steps steps = new Steps();
    private final String searchValue = "Natalya-N/qa-guru-lesson10";

    @Test
    void selenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[data-target='query-builder.input']").setValue(searchValue).pressEnter();
        $(linkText(searchValue)).click();
        $("#issues-tab").shouldHave(text("Issues"));
    }

    @Test
    void lambdaTest() {
        step("Найти 'Natalya-N/qa-guru-lesson10' с помощью строки поиска", () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("[data-target='query-builder.input']").setValue(searchValue).pressEnter();
        });
        step("Перейти по клику в репозиторий " + searchValue, () -> {
            $(linkText(searchValue)).click();
        });
        step("Проверить, что для репозитория"  + searchValue + " есть вкладка Issues", () -> {
            $("#issues-tab").shouldHave(text("Issues"));
        });
    }

    @Test
    void stepTest() {
        steps.clickOnSearch();
        steps.textSearchInput(searchValue);
        steps.openRepositoryByLink(searchValue);
        steps.checkEmptyIssuesList();
    }


}
