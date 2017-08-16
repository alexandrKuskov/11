package eu.datatiler.tests;


import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.qatestlab.base.BaseTest;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadEntitiesTest extends BaseTest {

    private String mayorFile = "Mayor+Elections+2013";
    private String cognatesFile = "cognates.xlsx";
    private String bigFile = "Test_File_BIG.xlsx";
    private List<String> files = new ArrayList<String>() {
        {
            add(bigFile);
            add(mayorFile);
            add(cognatesFile);


        }
    };

    @Test(description = "download  projects entities from dropbox" , priority = 1)
    public void downloadProjectEntity() throws Exception {
        Reporter.log("Open home page: " + BasePage.PROJECT_ENTITY_URL);
        driver().get(BasePage.PROJECT_ENTITY_URL);
        Actions.generalActions().clearSession();
        BaseActions.wait(5);
        File file = new File(downloadFilepath);
        if (!file.exists()){
            file.mkdir();
        }
        else {
            for (File myFile : file.listFiles()) {
                if (myFile.isFile()) myFile.delete();
            }
        }
        for(String fileName : files) {
            Pages.dropBoxPage().waitForDropBoxStartPage();
            Pages.dropBoxPage().selectFile(fileName);
            Pages.dropBoxPage().waitForFileNameVisibility(fileName);
            Pages.dropBoxPage().clickDownloadButton();
            Pages.dropBoxPage().selectDownloadOption();
            Pages.dropBoxPage().waitDownloadDropdownInvisibility();
            Pages.dropBoxPage().clickBackLink();
        }
        BaseActions.wait(25);
    }
}
