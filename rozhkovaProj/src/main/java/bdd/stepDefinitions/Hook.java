package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    WebDriverHelper webDriverHelper;

    public Hook(WebDriverHelper webDriverHelper) {//конструктор для того щоб picocontainer(з pom) міг передати об'єкт класу WebDriverHelper в клас Hook
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        webDriverHelper.getWebDriver().quit();
    }
}
