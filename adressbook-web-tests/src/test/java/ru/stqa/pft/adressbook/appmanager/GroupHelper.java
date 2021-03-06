package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.adressbook.model.GroupFields;
import ru.stqa.pft.adressbook.model.Groups;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void backToGroupsPage() {
    click(By.linkText("group page"));
  }

  public void submitGroup() {
      click(By.name("submit"));
  }

  public void fillGroupFields(GroupFields groupFields) {
    type(By.name("group_name"), groupFields.getGroupName());
    type(By.name("group_header"), groupFields.getGroupHeader());
    type(By.name("group_footer"), groupFields.getGroupFooter());

  }

  public void creationNewGroup() {
    click(By.name("new"));
    click(By.id("content"));

  }

  public void delete() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void select(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModified() {
    click(By.name("update"));
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(GroupFields group) {
    creationNewGroup();
    fillGroupFields(group);
    submitGroup();
    groupCashe = null;
    backToGroupsPage();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public Groups all() {

    if (groupCashe!=null) {
      return new Groups(groupCashe);
    }

    groupCashe = new Groups();
    List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCashe.add(new GroupFields().withId(id).withName(name));
    }
    return new Groups(groupCashe);
  }

  public void modify(GroupFields group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupFields(group);
    submitGroupModified();
    groupCashe = null;
    backToGroupsPage();
  }
  public void delete(int index) {
    select(index);
    delete();
    backToGroupsPage();
  }

  public void delete(GroupFields group) {
    selectGroupById(group.getId());
    delete();
    groupCashe = null;
    backToGroupsPage();
  }
  private Groups groupCashe = null;



}
