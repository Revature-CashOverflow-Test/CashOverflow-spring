@TrackMultiAccounts
Feature: Track Multiple Accounts
	Track multiple accounts created by 

	@TrackMultiAccounts1
	Scenario: Create a Checking Account
	Given the User logs in successfully
	When the User clicks on create a bank account
	And the User inputs the data into a corresponding form
	And the User selects checking account 
	And the User clicks create account
	Then the application shows a new checking account is created
	
	@TrackMultiAccounts2
	Scenario: Create a Savings Account
	Given the User logs in successfully
	When the User clicks on create a bank account
	And the User inputs the data into a corresponding form
	And the User selects savings account 
	And the User clicks create account
	Then the application shows a new savings account is created
	
	@TrackMultiAccounts3
	Scenario: Missing Form
	Given the User logs in successfully
	When The user clicks on create a bank account
	And the User clicks create account
	Then the application notifies the user that they need to fill in the missing form

	@TrackMultiAccounts4
	Scenario: View Bank Account
	Given the User logs in successfully
	When the User clicks on create my accounts
	And the User clicks between accounts to access
	Then the application displays all accounts
	
	