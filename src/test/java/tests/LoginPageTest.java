package tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    @Test(description = "Test successful login with valid credentials")
    @Description("Ensure that a user can log in with valid username and password.")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue("Inventory page should be displayed after login.", inventoryPage.isInventoryDisplayed());
    }

    @Test(description = "Test failed login with invalid credentials")
    @Description("Ensure that an error message is displayed when logging in with invalid credentials.")
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_pass");
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message should match expected.");
    }

    @Test(description = "Test logout functionality")
    @Description("Ensure that a user can log out successfully.")
    public void testLogout() {
        // Perform a successful login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Perform logout
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.logout();

        // Verify that login button is displayed again
        Assert.assertTrue("Login button should be displayed after logout.", loginPage.isLoginButtonDisplayed());
    }
}
