package org.nsu.fit.shared;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.openqa.selenium.By;

import static org.nsu.fit.services.browser.BrowserService.LOGIN_URI;

public class Screen {
    protected Browser browser;

    public Screen(Browser browser) {
        this.browser = browser;
    }

    public LoginScreen logout(){
        browser.openPage(LOGIN_URI);
        return new LoginScreen(browser);
    }

    public Screen clearAll(String id) {
        browser.clear(By.id(id));
        return this;
    }
}
