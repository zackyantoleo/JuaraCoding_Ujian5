package com.ujian;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class CheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void successfulCheckoutTest() throws InterruptedException {
        System.out.println("===Successful Checkout Test=== ");
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);
        productPage.addTwoProductsToCart();
        Thread.sleep(1000);
        productPage.goToCart();
        Thread.sleep(1000);
        cartPage.proceedToCheckout();
        Thread.sleep(1000);
        checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
        Thread.sleep(1000);
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));
    }

    @Test
    public void missingCheckoutDetailsTest()  throws InterruptedException{
        System.out.println("===Missing Checkout Details Test=== ");
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);
        productPage.addTwoProductsToCart();
        Thread.sleep(1000);
        productPage.goToCart();
        Thread.sleep(1000);
        cartPage.proceedToCheckout();
        Thread.sleep(1000);
        checkoutPage.enterCheckoutDetails("", "", "");
        Thread.sleep(1000);
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"));
    }

    @Test
    public void checkoutWithEmptyCartTest()  throws InterruptedException{
        System.out.println("===Checkout With Empty Cart Test=== ");
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);
        productPage.goToCart();
        Thread.sleep(1000);
        cartPage.proceedToCheckout();
        Thread.sleep(1000);
        checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
        Thread.sleep(1000);
        try {
            Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two.html"));
        } catch (AssertionError e) {
            System.out.println("Test Gagal");
            throw e; // rethrow to ensure the test fails
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
