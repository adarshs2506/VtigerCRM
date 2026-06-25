package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ProductsPage POM — Elements for the Products module.
 * @author Adarsh Singh
 */
public class ProductsPage {

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "img[title='Create Product...']")
    private WebElement createProductBtn;

    @FindBy(name = "productname")
    private WebElement productNameField;

    @FindBy(name = "productcode")
    private WebElement productCodeField;

    @FindBy(name = "unit_price")
    private WebElement unitPriceField;

    @FindBy(name = "qtyinstock")
    private WebElement qtyInStockField;

    @FindBy(name = "usageunit")
    private WebElement usageUnitField;

    @FindBy(name = "productcategory")
    private WebElement categoryDropdown;

    @FindBy(name = "manufacturer")
    private WebElement manufacturerDropdown;

    @FindBy(css = "input[title='Save [Alt+S]']")
    private WebElement saveBtn;

    @FindBy(className = "dvHeaderText")
    private WebElement headerText;

    public WebElement getCreateProductBtn()    { return createProductBtn;    }
    public WebElement getProductNameField()    { return productNameField;    }
    public WebElement getProductCodeField()    { return productCodeField;    }
    public WebElement getUnitPriceField()      { return unitPriceField;      }
    public WebElement getQtyInStockField()     { return qtyInStockField;     }
    public WebElement getUsageUnitField()      { return usageUnitField;      }
    public WebElement getCategoryDropdown()    { return categoryDropdown;    }
    public WebElement getManufacturerDropdown(){ return manufacturerDropdown;}
    public WebElement getSaveBtn()             { return saveBtn;             }
    public String getProductHeader()           { return headerText.getText();}
}
