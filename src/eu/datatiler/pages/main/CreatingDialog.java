package eu.datatiler.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import com.qatestlab.properties.Properties;
import org.apache.commons.lang3.SystemUtils;
import org.testng.Reporter;

public class CreatingDialog extends BasePage {

    private Locator dialog = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-content']");

    private Locator nameField = new Locator(LocatorTypes.CSS,
            ".uat_project_name");

    private Locator createButton = new Locator(LocatorTypes.CSS,
            ".uat_btn_create");

    private Locator cancelButton = new Locator(LocatorTypes.CSS,
            ".uat_btn_cancel");

    private Locator uploadFileButton = new Locator(LocatorTypes.CSS,
            ".uat_project_file");

    private Locator dialogHeader = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-header']//*[text()='%s']");

    private Locator progressBar = new Locator(LocatorTypes.XPATH,
            "//div[@value='uploader.progress']");

    private String winDir = "C:\\datatileProjects\\";
    private String linDir = "/tmp/datalineEntities/";
    private String mayorfile = "Mayor+Elections+2013%2C+ENG+-+weeks+encoded%2C+MR-groups.sav.zip";
    private String cognatesFile = "cognates.xlsx";
    private String bigFile = "Test_File_BIG.xlsx";

    protected CreatingDialog() {
    }

    public void waitForVisibility() {
        waitForVisibility("Wait for 'Creating' dialog visibility", dialog);
    }

    public boolean isCreatingDialogPresent(String title) {
        return isVisible("Check is creating dialog present", dialogHeader, title);
    }

    public void waitForInvisibility() {
        waitForInvisibility("Wait for 'Creating' dialog invisibility", dialog);
    }

    public boolean isProgressBarVisible() {
        return isVisible("Check is progress bar visible", progressBar);
    }

    public void typeName(String value) {
        waitForVisibility("wait for input field visibility", nameField);
        type("Type name: " + value, value, nameField);

    }

    public void waitForCreateButtonToBeClickable() {
        waitToBeClickable("Waiting for 'create button' to be clickable", createButton);
    }

    public void clickCreateButton() {
        click("Click 'Create' button", createButton);
    }

    public void clickCancelButton() {
        click("Click 'Cancel' button", cancelButton);
    }

    public boolean isCreateButtonDisabled() {
        return isAttributePresent("Checking if 'create' button is disabled", "disabled", createButton);
    }

    public void uploadProjectFile(String file) {
        String uploadFile = null;
        if (SystemUtils.IS_OS_WINDOWS){
            uploadFile = winDir + file;
        }
        if (SystemUtils.IS_OS_LINUX){
            uploadFile = linDir + file;
        }
       // String uploadFile = Properties.getUploadFile();
       // String uploadFile = "C:\\datatileProjects\\Mayor+Elections+2013%2C+ENG+-+weeks+encoded%2C+MR-groups.sav.zip";
        Reporter.log("path " + uploadFile);
        uploadFile("Uploading project file", uploadFile, uploadFileButton);
    }

    public void uploadEncodeFile(String file) {
        String uploadFile = null;
        if (SystemUtils.IS_OS_WINDOWS){
            uploadFile = winDir + file;
        }
        if (SystemUtils.IS_OS_LINUX){
            uploadFile = linDir + file;
        }
       // String uploadFile = Properties.getEncodeFile();
        Reporter.log("path " + uploadFile);
        uploadFile("Uploading encoder file", uploadFile, uploadFileButton);
    }

    public void uploadImageFile() {
        String uploadFile = Properties.getImageFile();
        uploadFile("Uploading image file", uploadFile, uploadFileButton);
    }

    public boolean isFileUploaded(String fileName) {
        return getText("Get uploaded file's path", uploadFileButton).contains(fileName);
    }
}
