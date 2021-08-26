Feature: logout
Scenario: when logged in, send a delete to logout

Background: 
	* def signin = call read('classpath:login/login.feature')

Given url 'http://localhost:8080/users'
And request { username : 'Quentin' }
When method delete
Then status 204
# There's no matching here because there's nothing to match
# Tried testing for both session being '#null' and '#notnull' and both return wrong because there's no session cookie to check
