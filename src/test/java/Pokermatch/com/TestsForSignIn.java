package Pokermatch.com;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static Pokermatch.com.WebLibrary.*;

public class TestsForSignIn {

    public static WebDriver driver = null;
    public static MainPage mainPage = null;

    @BeforeClass
    public static void setUp(){

        System.setProperty("webdriver.chrome.driver", "H://Geckodriver_Chrome//chromedriver.exe");
        cleanCash();
        mainPage = new MainPage(driver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void shutDown(){

        driver.quit();
    }

    @Test
    public void loginToGitHubAccount(){

        MainPage main = new MainPage(driver);

        goToUrl(driver,"https://github.com/");
        clickButton(main.buttonSignInGitHub);
        sendKeysWebElements(main.fieldLoginGitHub,"ViktorBibik",main.fieldPasswordGitHub,"deadmananor_1");
        clickButton(main.getButtonSignInLoginPopUpGitHub);
        Assert.assertEquals(main.linkPullRequests.getText(), "Pull requests");
        recievePageTitle(driver);
        recieveCookies(driver);
    }

    @Test
    public void testLoginExistingUser(){

        MainPage main = new MainPage(driver);

        goToUrl(driver, "https://beta.pokermatch.com/en#login");
        sendKeysWebElements(main.fieldEmailLogin,"test_bibik",main.fieldPassword,"111111");
        clickButton(main.buttonSignIn);
        Assert.assertEquals(main.elementNickName.getText(),"Your nick:");
        recievePageTitle(driver);
        recieveCookies(driver);
    }

}
