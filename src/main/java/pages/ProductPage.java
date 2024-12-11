package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;

    @FindBy(css = ".inventory_item:nth-child(1) .btn_inventory")
    private WebElement firstProduct;

    @FindBy(css = ".inventory_item:nth-child(2) .btn_inventory")
    private WebElement secondProduct;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addTwoProductsToCart() {
        firstProduct.click();
        secondProduct.click();
    }

    public void goToCart() {
        cartButton.click();
    }
}
