package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class CustomerTest implements TestInterface {

    @Test(description = "Show all customers.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Show customers feature.")
    public void getCustomerTest() {
        client.getCustomers(adminToken);
    }

    @Test(description = "Shows authenticated customer")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Show customers feature.")
    public void meTest() {
        client.me(adminToken);
    }
}
