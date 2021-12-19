package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;

public class AuthAsAdminAndCustomerTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Auth as admin")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature")
    public void authAsAdmin() {
        new LoginScreen(browser)
                .loginAsAdmin()
                .logout();
    }

    @Test(description = "Auth as created customer")
    @Severity(SeverityLevel.CRITICAL)
    public void authAsCustomer(){
        new LoginScreen((browser))
                .loginAsCustomer("sydneyvandervort@mail.com", "strongpass")
                .logout();
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
