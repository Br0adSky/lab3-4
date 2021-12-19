package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;

public class BadCustomerFirstNameTest implements TestInterface {

    @Test(description = "Create user with wrong first name.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void shortNameTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.firstName = "Ы";
        client.createUnValidCustomer(adminToken, customer);
    }

    @Test(description = "Create user with wrong first name.",expectedExceptions = BadRequestException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create customer feature.")
    public void nameNotStartsWithUpperCaseTest() {
        CustomerPojo customer = client.autoGenerateCustomer();
        customer.firstName = "pEtr";
        client.createUnValidCustomer(adminToken, customer);
    }
}
