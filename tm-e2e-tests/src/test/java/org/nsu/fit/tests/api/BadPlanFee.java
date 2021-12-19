package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;

public class BadPlanFee implements TestInterface{

    @Test(description = "Create plan with negative fee.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void feeIsNegativeTest(){
        PlanPojo plan = client.fillValidPlan();
        plan.fee = -1;
        plan = client.createUnValidPlan(adminToken,plan);
    }


    @Test(description = "Create plan with too high fee.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void feeIsHighTest(){
        PlanPojo plan = client.fillValidPlan();
        plan.fee = 100000;
        plan = client.createUnValidPlan(adminToken,plan);
    }
}
