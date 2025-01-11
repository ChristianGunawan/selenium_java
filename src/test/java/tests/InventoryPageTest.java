package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryPageTest extends BaseTest{
    @Test(description = "Test navigation to product details")
    @Description("Ensure that clicking on a product navigates to its details page.")
    public void testProductNavigation() {
        // Perform a successful login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Implement clicking on a product and verifying details
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryDisplayed(), "Inventory page should be displayed.");
    }

    @Test(description = "Test click product name")
    @Description("Ensure that clicking product navigates to its details page.")
    public void testClickProductName() {
        // Perform a successful login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Implement clicking on a product's name and verifying redirect to product detail
        InventoryPage inventoryPage = new InventoryPage(driver);
        String productNameBeforeClick = inventoryPage.getProductNameFromLink("item_4_title_link", "[data-test='inventory-item-name']");
        inventoryPage.clickProductName("item_4_title_link");
        String productNameAfterClick = inventoryPage.getProductNameFromDetailsPage("[data-test='inventory-item-name']");
        String currentUrl = driver.getCurrentUrl();

        // Assertion
        Assert.assertEquals(productNameBeforeClick, productNameAfterClick, "Product name should remain the same after clicking.");
        Assert.assertTrue(currentUrl.contains("/inventory-item"), "URL should contain '/inventory-item' after clicking on the product.");
    }
}

