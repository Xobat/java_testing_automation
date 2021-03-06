package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.adressbook.model.UserFields;
import ru.stqa.pft.adressbook.model.Users;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }
  public void backtoUsersPage() {
    click(By.id("logo"));
  }
  public void submitUser() {
    click(By.name("submit"));
  }
  public void updateUser() {
    click(By.name("update"));
  }

  public void fillUserFields(UserFields userFields) {
    type(By.name("firstname"), userFields.getFirstName());
    type(By.name("middlename"), userFields.getMiddleName());
    type(By.name("lastname"), userFields.getLastName());
    type(By.name("nickname"),userFields.getNickName());
    type(By.name("title"),userFields.getTitle());
    type(By.name("company"),userFields.getCompany());
    type(By.name("address"),userFields.getAddress());
    type(By.name("home"),userFields.getHome());
    type(By.name("mobile"),userFields.getMobile());
    type(By.name("work"),userFields.getWork());
    type(By.name("fax"),userFields.getFax());
    type(By.name("email"),userFields.getEmail1());
    type(By.name("email2"),userFields.getEmail2());
    type(By.name("email3"),userFields.getEmail3());
    type(By.name("homepage"),userFields.getHomepage());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("2");
    new Select(wd.findElement(By.name("bmonth"))).selectByValue("January");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("5");
    if (isElementPresent(By.name("new_group"))) {
      new Select(wd.findElement(By.name("amonth"))).selectByValue("January");
      if (userFields.getGroupID()>0) {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[3]")).isSelected()) {
          wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[3]")).click();
        }
      }
    } else {
      new Select(wd.findElement(By.name("amonth"))).selectByValue("january");
    }
    type(By.name("byear"),userFields.getbYear());
    type(By.name("ayear"),userFields.getaYear());
    type(By.name("address2"),userFields.getAddress2());
    type(By.name("nickname"),userFields.getNickName());
    type(By.name("phone2"),userFields.getPhone2());
    type(By.name("notes"),userFields.getNotes());
  }

  public Users all() {
    if (userCache !=null) {
      return userCache;
    }

    userCache = new Users();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      userCache.add(new UserFields().withId(id));
    }
    return new Users(userCache);
  }

  public void selectUserModified() {
      wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img")).click();

  }
  public void deleteSelectedUser () {
    wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
  }

  public void deleteUserFromDetails() {
    wd.findElement(By.xpath("//div[@id='content']/form[2]/input[2]")).click();
  }

  public void submitUserModified () {
    wd.findElement(By.xpath("//div[@id='content']/form[1]/input[22]")).click();
  }



  public void delete(UserFields user) {
    selectUserById(user.getId());
    wd.findElement(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input")).click();
    acceptAllert();
    userCache = null;
    backtoUsersPage();
  }
  public void goToAddNew() {
    click(By.linkText("add new"));
  }
  public void confirmPopUp() {

  }
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
  public void create(UserFields user) {
    goToAddNew();
    fillUserFields(user);
    submitUser();
    userCache = null;
    backtoUsersPage();
  }
  public void openUserById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }
  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void update(UserFields user) {
    openUserById(user.getId());
    fillUserFields(user);
    updateUser();
    userCache = null;
    backtoUsersPage();
  }
  private Users userCache = null;
}
