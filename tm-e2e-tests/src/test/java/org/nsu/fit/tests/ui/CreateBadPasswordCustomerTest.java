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

public class CreateBadPasswordCustomerTest {
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

    @Test(description = "Create user with wrong first name.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature.")
    public void shortPasswordTest() {
        String pass = "s";
        CustomerPojo customerPojo = createValidCustomer();
        customerPojo.pass = pass;
        screen.fillFirstName(customerPojo.firstName)
                .fillLastName(customerPojo.lastName)
                .fillLogin(customerPojo.login)
                .fillPassword(customerPojo.pass)
                .clickSubmit();
        screen.clearAll("password");
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert !customer.pass.equals(pass);
        }
    }

    @Test(description = "Create user with wrong first name.", dependsOnMethods = "shortPasswordTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void simplePasswordTest() {
        String pass = "123qwe";
        CustomerPojo customerPojo = createValidCustomer();
        customerPojo.pass = pass;
        screen.fillPassword(customerPojo.pass)
                .clickSubmit();
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert !customer.pass.equals(pass);
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
