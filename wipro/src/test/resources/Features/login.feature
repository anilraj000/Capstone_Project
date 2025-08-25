Feature: Login Functionality

Scenario: Verify Login successfull
Given Launch the Browser
And Navigate to the URL
When Enter the username and password
And Click on Login Button
Then Login should be successful
And verify dashboard
Then Close the browser

Scenario: Verify Login unsuccessfull
Given Launch the Browser
And Navigate to the URL
When Enter the invalid username and password
And Click on Login Button
Then Login should be successful
And verify dashboard
Then Close the browser