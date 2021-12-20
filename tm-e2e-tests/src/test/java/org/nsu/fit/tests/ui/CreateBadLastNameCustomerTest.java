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

public class CreateBadLastNameCustomerTest {
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

    @Test(description = "Create user with wrong last name - contains digits.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature.")
    public void nameContainsDigitsTest() {
        String name = "Petrov2";
        CustomerPojo customerPojo = createValidCustomer();
        customerPojo.lastName = name;
        screen.fillFirstName(customerPojo.firstName)
                .fillLastName(customerPojo.lastName)
                .fillLogin(customerPojo.login)
                .fillPassword(customerPojo.pass)
                .clickSubmit();
        screen.clearAll("lastName");
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert !customer.lastName.equals(name);
        }
    }

    @Test(description = "Create user with wrong last name - contains space.", dependsOnMethods = "nameContainsDigitsTest")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature.")
    public void nameNotStartsWithUpperCaseTest() {
        String name = "Petrov Petrov";
        CustomerPojo customerPojo = createValidCustomer();
        customerPojo.lastName = name;
        screen.fillLastName(customerPojo.lastName)
                .clickSubmit();
        List<CustomerPojo> customers = restClient.getCustomers(adminToken);
        for (CustomerPojo customer : customers) {
            assert !customer.lastName.equals(name);
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
