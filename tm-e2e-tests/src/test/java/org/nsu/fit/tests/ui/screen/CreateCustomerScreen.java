package org.nsu.fit.tests.ui.screen;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;

public class CreateCustomerScreen extends Screen {
    public CreateCustomerScreen(Browser browser) {
        super(browser);
    }

    private final CustomerPojo validCustomer = createValidCustomer();

    public static CustomerPojo createValidCustomer() {
        Name nameGenerator = new Faker().name();
        CustomerPojo customer = new CustomerPojo();
        customer.firstName = nameGenerator.firstName();
        customer.lastName = nameGenerator.lastName();
        customer.login = String.format("%s_%s@mail.com",
                customer.firstName.toLowerCase(),
                customer.lastName.toLowerCase());
        customer.pass = "strongpass";
        return customer;

    }

    public CreateCustomerScreen fillLogin(String login) {
        browser.typeText(By.id("login"), login);
        return this;
    }

    public CreateCustomerScreen fillPassword(String pass) {
        browser.typeText(By.id("password"), pass);
        return this;
    }

    public CreateCustomerScreen fillFirstName(String firstName) {
        browser.typeText(By.id("firstName"), firstName);
        return this;
    }

    public CreateCustomerScreen fillLastName(String lastName) {
        browser.typeText(By.id("lastName"), lastName);
        return this;
    }

    // Лабораторная 4: Подумайте как обработать ситуацию,
    // когда при нажатии на кнопку Submit ('Create') не произойдет переход на AdminScreen,
    // а будет показана та или иная ошибка на текущем скрине.
    public AdminScreen clickSubmit() {
        browser.click(By.xpath("//button[@type = 'submit']"));
        return new AdminScreen(browser);
    }

    public AdminScreen clickCancel() {
        return new AdminScreen(browser);
    }

    public CreateCustomerScreen fillBadFirstName(String name) {
        browser.typeText(By.id("firstName"), name);
        return this;
    }

}
