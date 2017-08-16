package eu.datatiler.pages.project.Settings;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import eu.datatiler.actions.Actions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class MetaEditorTab extends BasePage {

    private Locator tableProjectVariables = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'grids')]/div[1]");

    private Locator nameColumnVariableTable = tableProjectVariables.concat(new Locator(LocatorTypes.XPATH,
            "//div[contains(text(), 'Name')]"));

    private Locator tableProjectSubVariables = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'grids')]//div[contains(@class, 'variableGrid')]");

    private Locator nameColumnSubVariableTable = tableProjectSubVariables.concat(new Locator(LocatorTypes.XPATH,
            "//div[contains(text(), 'Name')]"));

    private Locator projectVariables = tableProjectVariables.concat(new Locator(LocatorTypes.XPATH,
            "//div[@class='dt-grid-table']/div[@style='top: 0px; height: 700px;']/div"));

    private Locator subVariables = tableProjectSubVariables.concat(new Locator(LocatorTypes.XPATH,
            "//tbody/tr/td[contains(@class, 'field_name')]//span"));

    private Locator projectVariable = tableProjectVariables.concat(new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']"));

    private Locator settingsPageSortButton = new Locator(LocatorTypes.XPATH, "//div[@class='maintools']/div/div/div[3]/div");

    private Locator directSortOption = new Locator(LocatorTypes.XPATH, "//div[contains(text(), 'By name asc')]");

    private Locator reverseSortOption = new Locator(LocatorTypes.XPATH, "//div[contains(text(), 'By name desc')]");


    protected MetaEditorTab() {
    }

    public void waitTobeLoaded() {
        waitForVisibility("Wait variables project table to be visible", tableProjectVariables);
    }

    public void clickNameColumnVarProjectTable() {
        waitToBeClickable("Wait 'Name' column header", nameColumnVariableTable);
        click("Click 'Name' column variables project table", nameColumnVariableTable);
    }

    public void clickNameColumnSubVarProjectTable() {
        waitToBeClickable("Wait 'Name' column header", nameColumnSubVariableTable);
        click("Click 'Name' column variables project table", nameColumnSubVariableTable);
    }

    public ArrayList<String> getVariablesProject() {

        Actions.generalActions().wait(5);

        ArrayList<String> list = new ArrayList<>();
        for (WebElement wEl : getElements("Get project variables", projectVariables)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(wEl.getText().trim());
        }
        return list;
    }

    public ArrayList<String> getSubVariablesProject() {

        Actions.generalActions().wait(5);

        ArrayList<String> list = new ArrayList<>();
        for (WebElement wEl : getElements("Get project subvariables", subVariables)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(wEl.getText().trim());
        }
        return list;
    }

    public void clickProjectVariable(String variableName) {
        click("Click '" + variableName + "' variable", projectVariable, variableName);
    }

    public void clickSortTool() {
        waitToBeClickable("Wait 'Sort' column header", settingsPageSortButton);
        click("Click sort tool", settingsPageSortButton);
    }

    public void clickDirectSortOption() {
        waitToBeClickable("Wait 'By name asc' option", directSortOption);
        click("Click 'By name asc' option variable", directSortOption);
    }

    public void clickReverseSortOption() {
        waitToBeClickable("Wait 'By name desc' option", reverseSortOption);
        click("Click 'By name desc' option variable", reverseSortOption);
    }


}
