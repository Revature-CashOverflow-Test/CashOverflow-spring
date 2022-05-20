@TrackMultiAccounts
Feature: Track Multiple Accounts
	Track multiple accounts created by 

	@TrackMultiAccounts1
	Scenario: Create a Checking Account
	Given the User logs in successfully
	When the User clicks on create a bank account
	And the User inputs the date into a corresponding form
	And the User selects checking account 
	And the User clicks create account
	Then the application shows a new checking account is created
	
