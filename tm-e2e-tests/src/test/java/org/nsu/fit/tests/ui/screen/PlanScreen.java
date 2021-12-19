package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;

public class PlanScreen extends Screen {
    public PlanScreen(Browser browser) {
        super(browser);
    }

    private final PlanPojo validPlan = createValidPlan();

    public static PlanPojo createValidPlan() {
        PlanPojo plan = new PlanPojo();
        plan.fee = 500;
        plan.name = "Plan on 500$";
        plan.details = "This plan was designed only for 500$";
        return plan;
    }

    public PlanScreen fillName(String name){
        browser.typeText(By.id("name"), name);
        return this;
    }
    public PlanScreen fillFee(int fee){
        browser.typeText(By.id("fee"), String.valueOf(fee));
        return this;
    }
    public PlanScreen fillDetails(String details){
        browser.typeText(By.id("details"), details);
        return this;
    }

    public AdminScreen clickSubmit() {
        browser.click(By.xpath("//button[@type = 'submit']"));
        return new AdminScreen(browser);
    }

    public AdminScreen clickCancel() {
        return new AdminScreen(browser);
    }
}
