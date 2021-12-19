package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;

public class BadCustomerPasswordTest implements TestInterface {

    @Test(description = "Create user with short password.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void shortPasswordTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.pass = "s";
        client.createUnValidCustomer(adminToken, customer);
    }

    @Test(description = "Create user with simple password.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void simplePasswordTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.pass = "123qwe";
        client.createUnValidCustomer(adminToken, customer);
    }
}
