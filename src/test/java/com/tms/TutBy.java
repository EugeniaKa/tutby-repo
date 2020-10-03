package com.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TutBy {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void jobsTutBy() {
        // открываем сайт
        driver.get("https://jobs.tut.by/");
        // находим поле поиска
        WebElement searchField = driver.findElement(By.cssSelector("[data-qa=search-input]"));
        // в поле Поиск пишем специальность, по которой ищем вакансии (в нашем случае - маркетолог)
        searchField.sendKeys("маркетолог");
        // находим кнопку "Найти"
        WebElement searchButton = driver.findElement(By.cssSelector("[data-qa=search-button]"));
        // нажимаем кнопку "Найти"
        searchButton.click();
        // находим и считываем заголовок
        String headerMarketer = driver.findElement(By.cssSelector("[data-qa=bloko-header-1]")).getText();
        // сравниваем заголовок с ожидаемым результатом
        assertEquals(headerMarketer, "Работа маркетологом в Минске");

        // получили коллекцию вакансий
        List<WebElement> companies = driver.findElements(By.cssSelector("[data-qa=vacancy-serp__vacancy-employer]"));
        // рандомом находим 3 компании, предлагающие вакансию
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(companies.size());
            // выводим названия компаний в консоль
            System.out.println(companies.get(x).getText());
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
