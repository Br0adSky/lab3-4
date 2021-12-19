package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.testng.annotations.Test;

public class PlanTest implements TestInterface {
    private PlanPojo planPojo;

    @Test(description = "Create plan.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Plan feature.")
    public void addNewPlanTest() {
        planPojo = client.createPlan(adminToken);
    }

    @Test(description = "Delete created plan.", dependsOnMethods = "addNewPlanTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Plan feature.")
    public void deletePlanTest() {
        client.deletePlan(adminToken, planPojo);
    }

}
