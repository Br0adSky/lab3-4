package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.nsu.fit.tests.ui.screen.CreateCustomerScreen.createValidCustomer;
import static org.nsu.fit.tests.ui.screen.PlanScreen.createValidPlan;

public class CreatePlanAndCustomerTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Create plan.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void addNewPlanTest() {
        PlanPojo plan = createValidPlan();
        new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan()
                .fillName(plan.name)
                .fillDetails(plan.details)
                .fillFee(plan.fee)
                .clickSubmit();
    }

    @Test(description = "Create customer via UI.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature")
    public void createCustomer() {
        CustomerPojo customerPojo = createValidCustomer();
        new AdminScreen(browser)
                .createCustomer()
                .fillFirstName(customerPojo.firstName)
                .fillLastName(customerPojo.lastName)
                .fillLogin(customerPojo.login)
                .fillPassword(customerPojo.pass)
                .clickSubmit();
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
