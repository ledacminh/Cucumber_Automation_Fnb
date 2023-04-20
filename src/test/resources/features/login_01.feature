@login01
Feature: Login to FnB

  #Background:
   # Given Open Fnb system

  @tag01
  Scenario:  Login Successfully no parameter
    When Input to name store
    And Input to user name
    And Input to password
    And Click to button manage
   # And Close application

  @tag01
  Scenario:  Login Successfully no parameter
    When Input to name store
    And Input to user name
    And Input to password
    And Click to button manage
    And Close application
  #mvn clean verify -D.filter.tags="@tag01"

