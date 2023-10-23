package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardApplicationTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void formSubmitTest() throws InterruptedException {
        driver.get("http://localhost:9999/" );
        WebElement form = driver.findElement(By.className("form" ));
        form.findElement(By.cssSelector("[data-test-id=name] input" )).sendKeys("Алексей Яшин" );
        form.findElement(By.cssSelector("[data-test-id=phone] input" )).sendKeys("+79999999999" );
        form.findElement(By.cssSelector("[data-test-id=agreement]" )).click();
        form.findElement((By.className("button" ))).click();
        String text = driver.findElement((By.cssSelector("[data-test-id=order-success]" ))).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
        Thread.sleep(5000);
    }
}

