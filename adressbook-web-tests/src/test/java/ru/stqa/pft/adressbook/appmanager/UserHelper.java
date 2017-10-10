package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.adressbook.model.UserFields;

public class UserHelper extends HelperBase {

  public UserHelper(FirefoxDriver wd) {
    super(wd);
  }
  public void backtoUsersPage() {
    click(By.linkText("logo"));
  }
  public void submitUser() {
    click(By.name("submit"));
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
    type(By.name("ayear"),userFields.getaYear());
    type(By.name("address2"),userFields.getAddress2());
    type(By.name("nickname"),userFields.getNickName());
    type(By.name("phone2"),userFields.getPhone2());
    type(By.name("notes"),userFields.getNotes());
  }

}