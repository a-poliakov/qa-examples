package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Описание:
 * 1. Пользователь открывает страницу аутентификации;
 * 2. Пользователь производит ввод валидных логина и пароля;
 * 3. Пользователь удостоверяется в успешной аутентификации — об этом свидетельствует имя пользователя
 * в верхнем правом углу окна;
 * 4. Пользователь осуществляет выход из аккаунта путем нажатия на имя пользователя в верхнем правом углу
 * окна с последующим нажатием на кнопку «Выйти…».
 */
public class LoginTest {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeAll
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    @Disabled
    public void loginTest() {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //получаем отображаемый логин
        String user = profilePage.getUserName();
        //и сравниваем его с логином из файла настроек
        assertEquals(ConfProperties.getProperty("login"), user);
    }

    /**
     * осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterAll
    public static void tearDown() {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit();
    }
}
