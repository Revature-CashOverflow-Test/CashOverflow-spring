@Login
Feature: Login	
User logs into CashOverflow account

@Login1
Scenario: Incorrect Password
Given the user is on the homepage
And the User is not logged in
When the User inputs "testingExample" into Username form
And the User inputs "1234225" into Password form
And the User clicks the log in button
Then the app displays incorrect password

@Login2
Scenario: Username Does Not Exist
 Given the User is in homepage
 And the User is not logged in
 When the User inputs "askjsh jkashkhjkd" into the Username form
 And the User inputs "Password!1" into the Password form
 And the User clicks log in button
 Then the app displays username doesn’t exist
 
@Login3
Scenario: Invalid Input
Given the User is on the homepage
And the User is not logged in
When the User inputs "jafjjkah" into Username form
And the User inputs "poqenkan" into Password form
And the User clicks the log in button
Then the app displays invalid credentials

@Login4
Scenario: Successfully Login
Given the User is in homepage
And the User is not logged in
When the User inputs "testingExample" into Username form
When the User inputs "Password!1" into Password form
And the User clicks the log in button
Then the app displays Login Successfully