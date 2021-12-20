package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.nsu.fit.tests.ui.screen.PlanScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.nsu.fit.tests.api.TestInterface.adminToken;

public class CreateBadDetailsPlanTest {
    private Browser browser = null;
    private RestClient restClient;
    private PlanScreen screen;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
        restClient = new RestClient();
        screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan();

    }

    @Test(description = "Create plan with short details.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create plan feature.")
    public void shortDetailsTest() {
        PlanPojo plan = restClient.fillValidPlan();
        String badField = "1";
        plan.details = badField;
        screen.fillDetails(plan.details)
                .fillFee(plan.fee)
                .fillName(plan.name)
                .clickSubmit();
        screen.clearAll("details");
        List<PlanPojo> plans = restClient.getPlans(adminToken);
        for (PlanPojo pln : plans) {
            assert !pln.details.equals(badField);
        }
    }

    @Test(description = "Create plan with long details.", dependsOnMethods = "shortDetailsTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create plan feature.")
    public void longDetailsTest() {
        PlanPojo plan = restClient.fillValidPlan();
        String badField = "I would like to start my intervention with a question: who among you would like to see his brother, his sister, a relative whom he likes very much to be rejected during a work interview or during the registration in an educational establishment either because of a criminal record which considerably limits his opportunities? The answer is no one\n" +
                "\n" +
                "My take on this topic is: Drug addicts should be treated, not punished.\n" +
                "\n" +
                "Drug addiction is a medical problem, a treatable brain disorder, not a character defect or form of social deviance. Substance abuse must not be criminalized in the interests of both the well-being of the people and health equity. Because imprisonment, whether for drug crimes or other related offenses, actually carries a much higher risk of drug overdose on release. According to statistics from JBS newspaper, more than half of those punished and imprisoned for drug addiction suffer from an untreated substance use disorder, and the use of drugs and illicit drugs generally increases significantly. after a period of imprisonment. In an untreated opioid use disorder, for example, relapse into drug use can be fatal due to the loss of opioid tolerance that may have occurred during the period of incarceration of the person.\n" +
                "\n" +
                "The damaging effects of punishment for crimes committed because of drug addiction have a disproportionate impact on the lives of the people. Imprisonment leads to solitary confinement, an aggravating factor in drug abuse, addiction and relapse, which increases the chances of committing the same crimes after release. It also increases the risk of premature death due to a wide variety of causes.\n" +
                "\n" +
                "After incarceration, the individual has a criminal record which severely limits future opportunities such as higher education and employment. This excessive burden of convictions and jail terms for drug addiction is taking its toll on children and families. Parents who are arrested can lose custody of their children, bringing them into the child protection system. This burden reinforces poverty by limiting upward mobility through impeded access to employment, housing, higher education and eligibility to vote. It also adversely affects the health of incarcerated people, their non-incarcerated family members and their communities. So based on the above, I can conclude that Drug addicts should be treated, not punished.";
        plan.details = badField;
        screen.fillDetails(plan.details)
                .clickSubmit();
        screen.clearAll("details");
        List<PlanPojo> plans = restClient.getPlans(adminToken);
        for (PlanPojo pln : plans) {
            assert !pln.details.equals(badField);
        }
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }
}
