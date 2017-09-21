package se.citerus.dddsample.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;
import se.citerus.dddsample.Application;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DummyAcceptanceTest {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void adminSiteCargoListContainsCannedCargo() throws InterruptedException {
        WebDriver driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(context).build();
        driver.get("http://localhost:8080/dddsample/");
        assertNotEquals("", driver.getPageSource());
    }
}