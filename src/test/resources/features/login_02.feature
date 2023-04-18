@login02
Feature: Login to FnB

  @tag
  Scenario:  Login Successfully no parameter
    Given Open Fnb system
    When Input to name store
    And Input to user name
    And Input to password
    And Click to button manage
    And Close application

  @tag
  Scenario:  Login Successfully have parameter
    Given Open Fnb system
    When Input to name store with "autotest1111"
    And Input to user name with "0966152432"
    And Input to password with "HaNoi@2023"
    And Click to button manage
    And Close application

  @tag
  Scenario Outline:  Login Successfully with username <username> and password <password>
    Given Open Fnb system
    When Input to name store with "autotest1111"
    And Input to user name with <username> and password with <password>
    And Click to button manage
    And Close application

    Examples:
      | username   | password   |
      | 0966152432 | HaNoi@2023 |

  @tag
  Scenario Outline:  Login Successfully using Datatable
    Given Open Fnb system
    When Input to name store with "autotest1111"
    And Input to user name with user name and password
      | Username   | Password   |
      | <username> | <password> |
    And Click to button manage
    And Close application

    Examples:
      | username   | password   |
      | 0966152432 | HaNoi@2023 |
