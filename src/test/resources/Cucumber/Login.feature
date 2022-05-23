Feature: Login	

@Login1
Scenario: Incorrect Password
Given the user is on the homepage
And the user enters the correct username 
And the user enters an incorrect password
When the user clicks the log in button
Then the app displays incorrect password

@Login2
Scenario: Username Does Not Exist
 Given the user is in homepage
 And the user enters a username that doesn’t exist
 And the user enters a password
 When the user clicks log in button
 Then the app displays username doesn’t exist
 
@Login3
Scenario: Invalid Input
Given the user is on the homepage
And the user enters the incorrect username
And the user enters the incorrect password
When the user clicks the log in button
Then the app displays invalid credentials

@Login4
Scenario: Incorrect Password
Given the user is on the homepage
And the user enters the correct username 
And the user enters an incorrect password
When the user clicks the log in button
Then the app displays incorrect password

@Login5
Scenario: Successfully Login
Given Scenario: Incorrect Password
Given the user enters the correct username
And the user enters the correct password
When the user clicks the log in button
Then the app displays Login Successfully