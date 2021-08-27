Feature: Login as a user and customer can view the items for sale by sorting the price.
Scenario: As a logged in user, send a get request to sort by price url and browse items sorted by price.
    
Background: 
	* def signin = call read('classpath:login/login.feature')
	
Given url 'http://localhost:8080/items/quentin/searchItem' 
And header price = '620.0'
And cookie SESSION = signin.sessionCookie
When method get
Then status 200
And match each response contains { uuid: '#notnull', price: '#? _ <= 620.0' }


