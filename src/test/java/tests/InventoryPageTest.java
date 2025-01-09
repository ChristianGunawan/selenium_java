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
}

