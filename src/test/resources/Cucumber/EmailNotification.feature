#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@EmailNotification
Feature: EmailNotification
  User turns on Email notifications

  @tag1
  Scenario: Turning on Email Notification
    Given the User is in homepage
		When the User logs in successfully
    And the user click the settings button 
    And the user turns on notification
    And user enters Amount threshold
    Then the user clicks save settings
    And the message displays user has turned on notifications

  