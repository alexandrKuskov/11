package eu.datatiler.pages.project;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class ExportDialog extends BasePage {

    private Locator region = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-dialog']");

    private Locator exportButton = new Locator(LocatorTypes.XPATH,
            "//span[text()='Export']");

    private Locator exportToDashboardRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='export2dashboards']");

    private Locator exportToNewDashboard = new Locator(LocatorTypes.XPATH,
            "//div[span[text()='Create new dashboard']]");

    private Locator inputFieldNewDashboardName = new Locator(LocatorTypes.XPATH,
            "//div[text()='Dashboard name']/following-sibling::input");

    private Locator alertSuccess = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'alert-success')]/div");

    private Locator exportToSpecialFormat = new Locator(LocatorTypes.XPATH,
            "//div[@role='toolbar']//div[3]//button[contains(@title,'Export to PowerPoint')]");

    private Locator formatTypeDropDown = exportToSpecialFormat.concat(new Locator(LocatorTypes.XPATH,
            "/following-sibling::ul"));

    private Locator formatTypeRegion = formatTypeDropDown .concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='PowerPoint (.pptx)']"));


    protected ExportDialog() {
    }

    public void clickExportToFormat(){
        waitForVisibility("Waiting for 'Export to PowerPoint or Excel' button visibility", exportToSpecialFormat);
        click("click 'Export to PowerPoint or Excel' button ", exportToSpecialFormat);
    }

    public void waitForExportToNewDashboard() {
        waitForVisibility("Waiting for 'New dashboard' visibility", exportToDashboardRegion);
    }

    public boolean isExportToDashboardRegionVisible() {
        return isVisible("Check export to dashboard region is visible", exportToDashboardRegion);
    }

    public void waitForNewDashboardNameInputField() {
        waitForVisibility("Waiting for 'New dashboard' visibility", inputFieldNewDashboardName);
    }

    public boolean isNewDashboardNameFieldVisible() {
        return isVisible("Check new dashboard field name is visible", inputFieldNewDashboardName);
    }

    public void clickExportToNewDashboard() {
        waitToBeClickable("wait for 'create new dashboard' button be clickable",exportToNewDashboard);
        click("Clicking 'New dashboard'", exportToNewDashboard);
    }

    public void waitForLoad() {
        waitForVisibility("Waiting for 'Export' dialog visibility", region);
    }

    public void setDashboardName(String dashboardName) {
        type("Set name '" + dashboardName + "'", dashboardName, inputFieldNewDashboardName);
    }

    public boolean isSuccessAlertVisible() {
        return isVisible("Check success alert is visible", alertSuccess);
    }

    public String getSuccessAlertText() {
        return getText("Get text success alert", alertSuccess);
    }

    public void clickExportButton() {
        waitToBeClickable("wait 'export' button be clickable", exportButton);
        click("Click 'export' button", exportButton);
    }

}
