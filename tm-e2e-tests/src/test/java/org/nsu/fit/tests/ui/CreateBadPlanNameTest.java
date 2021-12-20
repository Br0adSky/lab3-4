package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.nsu.fit.tests.ui.screen.PlanScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.nsu.fit.tests.api.TestInterface.adminToken;

public class CreateBadPlanNameTest {
    private Browser browser = null;
    private RestClient restClient;
    private PlanScreen screen;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
        restClient = new RestClient();
        screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan();

    }

    @Test(description = "Create plan with short name.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create plan feature.")
    public void feeIsNegativeTest() {
        PlanPojo plan = restClient.fillValidPlan();
        String badField = "u";
        plan.name = badField;
        screen.fillDetails(plan.details)
                .fillFee(plan.fee)
                .fillName(plan.name)
                .clickSubmit();
        screen.clearAll("name");
        List<PlanPojo> plans = restClient.getPlans(adminToken);
        for (PlanPojo pln : plans) {
            assert pln.name != badField;
        }
    }

    @Test(description = "Create plan with long name.", dependsOnMethods = "feeIsNegativeTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void feeIsHighTest() {
        PlanPojo plan = restClient.fillValidPlan();
        String badField = "I would like to start my intervention with a question: who among you would like to see his brother, his sister, a relative whom he likes very much";
        plan.name = badField;
        screen.fillFee(plan.fee)
                .clickSubmit();
        screen.clearAll("name");
        List<PlanPojo> plans = restClient.getPlans(adminToken);
        for (PlanPojo pln : plans) {
            assert pln.name != badField;
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
