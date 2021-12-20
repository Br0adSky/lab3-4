package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.tests.ui.screen.CreateCustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.nsu.fit.tests.api.TestInterface.adminToken;
import static org.nsu.fit.tests.ui.screen.CreateCustomerScreen.createValidCustomer;

public class CreateBadLoginCustomerTest {
    private Browser browser = null;
    private RestClient restClient;
    private CreateCustomerScreen screen;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
        restClient = new RestClient();
        screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer();

    }

    @Test(description = "Not contains .domain in login.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature.")
    public void wrongDomainLoginTest() {
        String login = "john_wick@example";
        CustomerPojo customerPojo = createValidCustomer();
        customerPojo.lastName = login;
        screen.fillFirstName(customerPojo.firstName)
                .fillLastName(customerPojo.lastName)
                .fillLogin(customerPojo.login)
                .fillPassword(customerPojo.pass)
                .clickSubmit();
        screen.clearAll("login");
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert !customer.lastName.equals(login);
        }
    }

    @Test(description = "Not contains @ in login.", dependsOnMethods = "wrongDomainLoginTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void wrongAtLoginTest() {
        String name = "john_wickexample.com";
        CustomerPojo customerPojo = createValidCustomer();
        customerPojo.login = name;
        screen.fillLastName(customerPojo.login)
                .clickSubmit();
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert !customer.login.equals(name);
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
