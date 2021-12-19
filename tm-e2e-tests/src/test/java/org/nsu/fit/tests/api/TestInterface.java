package org.nsu.fit.tests.api;

import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;

public interface TestInterface {
    RestClient client = new RestClient();
    AccountTokenPojo adminToken = client.authenticate("admin", "setup");
}
