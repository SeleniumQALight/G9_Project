package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import pages.PageProvider;

public class MainSteps {
    //отримати вебдрайвер і створити пейдж провайдер
    protected WebDriverHelper webDriverHelper;
    protected PageProvider pageProvider;

    public MainSteps(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        pageProvider = new PageProvider(webDriverHelper.getWebDriver());//даємо пейдж провайдеру вебдрайвер
    }
}
