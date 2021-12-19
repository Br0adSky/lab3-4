package org.nsu.fit.services.browser;

public class BrowserService {
    // Note: change url if you want to use the docker compose.
//    private static final String AUTH_URI = "http://localhost:8080/tm-frontend";
    public static final String AUTH_URI = "http://localhost:8090/tm-frontend";
    public static final String ADMIN_URI = AUTH_URI + "/admin";
    public static final String CREATE_CUSTOMER_URI = AUTH_URI + "/add-customer";
    public static final String CREATE_PLAN_URI = AUTH_URI + "/add-plan";
    public static final String LOGIN_URI = AUTH_URI + "/login";

    public static Browser openNewBrowser() {
        return new Browser().openPage(AUTH_URI);
    }
}
