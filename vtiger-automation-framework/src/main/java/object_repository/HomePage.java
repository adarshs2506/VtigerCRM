package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * HomePage POM — Navigation links + profile/logout elements.
 *
 * @author Adarsh Singh
 */
public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // ===== Navigation Module Links =====

    @FindBy(linkText = "Organizations")
    private WebElement orgLink;

    @FindBy(linkText = "Contacts")
    private WebElement conLink;

    @FindBy(linkText = "Opportunities")
    private WebElement oppLink;

    // FIX 1: New module links added
    @FindBy(linkText = "Leads")
    private WebElement leadLink;

    @FindBy(linkText = "Products")
    private WebElement productLink;

    @FindBy(linkText = "Documents")
    private WebElement documentLink;

    @FindBy(linkText = "Cases")
    private WebElement casesLink;

    @FindBy(linkText = "Calendar")
    private WebElement calendarLink;

    // ===== Profile / Logout =====

    @FindBy(css = "img[src='themes/softed/images/user.PNG']")
    private WebElement profileIcon;

   
    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    // ===== Getters =====

    public WebElement getOrgLink()      { return orgLink;      }
    public WebElement getConLink()      { return conLink;       }
    public WebElement getOppLink()      { return oppLink;       }
    public WebElement getLeadLink()     { return leadLink;      }
    public WebElement getProductLink()  { return productLink;   }
    public WebElement getDocumentLink() { return documentLink;  }
    public WebElement getCasesLink()    { return casesLink;     }
    public WebElement getCalendarLink() { return calendarLink;  }
    public WebElement getProfileIcon()  { return profileIcon;   }
    public WebElement getSignOutLink()  { return signOutLink;   }
}
