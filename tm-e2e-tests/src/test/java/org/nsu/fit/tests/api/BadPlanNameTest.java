package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;

public class BadPlanNameTest implements TestInterface{

    @Test(description = "Create plan with short name.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void shortNameTest(){
        PlanPojo plan = client.fillValidPlan();
        plan.name = "u";
        client.createUnValidPlan(adminToken,plan);
    }

    @Test(description = "Create plan with long name.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void longNameTest(){
        PlanPojo plan = client.fillValidPlan();
        plan.name = "I would like to start my intervention with a question: who among you would like to see his brother, his sister, a relative whom he likes very much";
        client.createUnValidPlan(adminToken,plan);
    }
}
