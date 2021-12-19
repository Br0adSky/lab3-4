package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;

public class BadLoginTest implements TestInterface {

    @Test(description = "Not contains .domain in login.", expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void wrongDomainLoginTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.login = "john_wick@example";
        client.createUnValidCustomer(adminToken, customer);
    }

    @Test(description = "Not contains @ in login.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void wrongAtLoginTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.login = "john_wickexample.com";
        client.createUnValidCustomer(adminToken, customer);
    }
}
