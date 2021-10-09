package fixtures.web;

import exception.CustomException;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class CreateAdminUserFixture {

    private static LoginPage loginPage;
    private static HomePage homePage;
    public CreateAdminUserFixture(){}


    public static Boolean checkLoginPageIsVisible(){
        loginPage = BasePage.navigateToLoginPage();
        return loginPage.pageComponentsAreVisible();
    }

    public static Boolean createAdminUser(String userName, String password) throws CustomException {
        loginPage.enterLoginDetails(userName,password);
        return true;
    }

    public static Boolean checkHomePageAppeared(){
        homePage = BasePage.navigateToHomePage();
        return homePage.checkHomepageContainsDashboardEndpoint();
    }
}
