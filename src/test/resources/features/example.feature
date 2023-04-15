Feature: ACCOUNT

  @tag
  Scenario:  Create New Customer
    Given I open New Customer page
    When Input to New Customer form with data
    And I click to Submit button at New Customer page
    Then Verify message displayed with data
    And I verify all above infomation created success