Feature: Login as a user and customer can view all the available items for sale.
Scenario: As a logged in user, send a get request to the get item url and browse all the items for sale.

Background: 
	* def signin = call read('classpath:login/login.feature')
	
Given url 'http://localhost:8080/items/quentin'
And request {}
And cookie SESSION = signin.sessionCookie
When method get
Then status 200
And match each response contains { uuid : '#notnull'}