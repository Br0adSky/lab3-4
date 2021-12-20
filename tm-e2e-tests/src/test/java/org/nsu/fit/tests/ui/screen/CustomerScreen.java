package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;

import static org.nsu.fit.services.browser.BrowserService.TOP_UP_BALANCE_URI;

public class CustomerScreen extends Screen {
    public CustomerScreen(Browser browser) {
        super(browser);
    }

    public TopUpBalanceScreen topUpBalance(){
        browser.openPage(TOP_UP_BALANCE_URI);
        return new TopUpBalanceScreen(browser);
    }
}
