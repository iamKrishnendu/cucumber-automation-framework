Feature: Demo HR Application Admin flow

  @demoWeb
  Scenario Template: Login to Orange HRM
      Given I am on login page
      When I enter "<username>" and "<password>"
      Then I should be on Home page

      Examples:
      |username|password|
      |admin   |admin123|