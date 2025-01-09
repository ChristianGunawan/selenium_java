package pages;

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

    /**
     * Performs logout action.
     */
    public void logout() {
        menuButton.click();
        logoutLink.click();
    }
}


