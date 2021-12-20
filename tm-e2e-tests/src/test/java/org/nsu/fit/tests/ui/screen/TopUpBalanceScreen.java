package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;

public class TopUpBalanceScreen extends Screen {
    public TopUpBalanceScreen(Browser browser) {
        super(browser);
    }

    public TopUpBalanceScreen fillMoney(int money){
        browser.typeText(By.id("topUpBalance"), String.valueOf(money));
        return this;
    }

    public CustomerScreen clickSubmit() {
        browser.click(By.xpath("//button[@type = 'submit']"));
        return new CustomerScreen(browser);
    }

}
