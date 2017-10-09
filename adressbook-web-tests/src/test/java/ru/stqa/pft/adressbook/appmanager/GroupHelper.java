package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.adressbook.model.GroupFields;

public class GroupHelper extends HelperBase{

  public GroupHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void backtoGroupsPage() {
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

  public void deleteSelectedGroup() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void selectGroup() {
      if (!wd.findElement(By.xpath("//div[@id='content']/form/span[2]/input")).isSelected()) {
        click(By.xpath("//div[@id='content']/form/span[2]/input"));
      }
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModified() {
    click(By.name("update"));
  }
}