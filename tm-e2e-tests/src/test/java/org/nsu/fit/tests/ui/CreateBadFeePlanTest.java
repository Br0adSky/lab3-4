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

public class CreateBadFeePlanTest {
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

    @Test(description = "Create plan with negative fee.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create plan feature.")
    public void feeIsNegativeTest() {
        PlanPojo plan = restClient.fillValidPlan();
        int badField = -1;
        plan.fee = badField;
        screen.fillDetails(plan.details)
                .clearAll("fee")
                .fillFee(plan.fee)
                .fillName(plan.name)
                .clickSubmit();
        screen.clearAll("fee");
        List<PlanPojo> plans = restClient.getPlans(adminToken);
        for (PlanPojo pln : plans) {
            assert pln.fee != badField;
        }
    }

    @Test(description = "Create plan with too high fee.", dependsOnMethods = "feeIsNegativeTest")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create plan feature.")
    public void feeIsHighTest() {
        PlanPojo plan = restClient.fillValidPlan();
        int badField = 100000;
        plan.fee = badField;
        screen.fillFee(plan.fee)
                .clickSubmit();
        screen.clearAll("fee");
        List<PlanPojo> plans = restClient.getPlans(adminToken);
        for (PlanPojo pln : plans) {
            assert pln.fee != badField;
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
