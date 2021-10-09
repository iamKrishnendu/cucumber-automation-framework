package pages;

public interface PageObject {

    // Implement to wait for a page to be loaded completely
    void waitForPageLoad();

    // Implement to ensure expected page is actually loaded
    Boolean isPageLoaded();
}
