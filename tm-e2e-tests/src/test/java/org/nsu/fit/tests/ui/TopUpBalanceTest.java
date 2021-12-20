package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.nsu.fit.tests.ui.screen.TopUpBalanceScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.nsu.fit.services.browser.BrowserService.TOP_UP_BALANCE_URI;
import static org.nsu.fit.tests.api.TestInterface.adminToken;

public class TopUpBalanceTest {
    private Browser browser = null;
    private RestClient restClient;
    private TopUpBalanceScreen screen;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
        restClient = new RestClient();
        screen = new LoginScreen(browser)
                .loginAsCustomer("sydneyvandervort@mail.com", "strongpass")
                .topUpBalance();

    }

    @Test(description = "Top up balance good.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Top up balance feature.")
    public void topUpBalanceTest() {
        int money = 500;
        screen.clearAll("topUpBalance");
        screen.fillMoney(money)
                .clickSubmit();
        browser.openPage(TOP_UP_BALANCE_URI);
    }

    @Test(description = "Top up balance bad.", dependsOnMethods = "topUpBalanceTest")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Top up balance feature.")
    public void wrongTopUpBalanceTest() {
        int money = -1;
        screen.clearAll("topUpBalance");
        screen.fillMoney(money)
                .clickSubmit();
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert customer.balance != money;
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
