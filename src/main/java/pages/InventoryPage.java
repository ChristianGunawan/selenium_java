package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_item")
    private WebElement inventoryItem;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "item_4_title_link")
    private WebElement firstInventoryItemName;

    @FindBy(id = "item-4-img-link")
    private WebElement firstInventoryItemImg;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if the inventory items are displayed on the page.
     *
     * @return true if inventory items are displayed, false otherwise.
     */
    public boolean isInventoryDisplayed() {
        return inventoryItem.isDisplayed();
    }

    public void clickProductName() {
        firstInventoryItemName.click();
    }

    public void clickProductImg() {
        firstInventoryItemImg.click();
    }


    /**
     * Performs action.
     */
    public void logout() {
        menuButton.click();
        logoutLink.click();
    }

    public String getProductNameFromLink(String productLinkId, String dataTestAttribute) {
        WebElement productLink = driver.findElement(By.id(productLinkId));
        return productLink.findElement(By.cssSelector(dataTestAttribute)).getText();
    }

    public void clickProductName(String productLinkId) {
        WebElement productLink = driver.findElement(By.id(productLinkId));
        productLink.click();
    }

    public String getProductNameFromDetailsPage(String productNameSelector) {
        WebElement productNameElement = driver.findElement(By.cssSelector(productNameSelector));
        return productNameElement.getText();
    }

}


