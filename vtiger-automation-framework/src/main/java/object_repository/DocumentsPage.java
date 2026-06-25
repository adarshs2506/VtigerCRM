package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * DocumentsPage POM — Elements for the Documents module.
 * @author Adarsh Singh
 */
public class DocumentsPage {

    public DocumentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "img[title='Create Document...']")
    private WebElement createDocumentBtn;

    @FindBy(name = "notes_title")
    private WebElement titleField;

    @FindBy(name = "folderid")
    private WebElement folderDropdown;

    @FindBy(name = "filename")
    private WebElement fileUploadField;

    @FindBy(css = "input[title='Save [Alt+S]']")
    private WebElement saveBtn;

    @FindBy(className = "dvHeaderText")
    private WebElement headerText;

    public WebElement getCreateDocumentBtn() { return createDocumentBtn; }
    public WebElement getTitleField()        { return titleField;        }
    public WebElement getFolderDropdown()    { return folderDropdown;    }
    public WebElement getFileUploadField()   { return fileUploadField;   }
    public WebElement getSaveBtn()           { return saveBtn;           }
    public String getDocumentHeader()        { return headerText.getText();}
}
