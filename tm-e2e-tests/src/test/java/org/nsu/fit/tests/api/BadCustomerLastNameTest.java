package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;

public class BadCustomerLastNameTest implements TestInterface{

    @Test(description = "Create user with wrong last name - contains digits.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void nameContainsDigitsTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.lastName = "Petrov2";
        client.createUnValidCustomer(adminToken, customer);
    }

    @Test(description = "Create user with wrong last name - contains symbols.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void nameContainsSymbolTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.lastName = "Petrov&";
        client.createUnValidCustomer(adminToken, customer);
    }

    @Test(description = "Create user with wrong last name - contains space.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void nameContainsSpaceTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.lastName = "Petrov Petrov";
        client.createUnValidCustomer(adminToken, customer);
    }

}
