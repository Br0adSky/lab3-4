package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;

import static org.nsu.fit.services.browser.BrowserService.*;

public class AdminScreen extends Screen {
    public AdminScreen(Browser browser) {
        super(browser);
    }

    public CreateCustomerScreen createCustomer() {
        browser.openPage(CREATE_CUSTOMER_URI);
        return new CreateCustomerScreen(browser);
    }

    public PlanScreen createPlan(){
        browser.openPage(CREATE_PLAN_URI);
        return new PlanScreen(browser);
    }
}
