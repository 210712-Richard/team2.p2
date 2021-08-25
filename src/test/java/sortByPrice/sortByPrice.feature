Feature: Login as a user and customer can view the items for sale by sorting the price.
Scenario: As a logged in user, send a get request to sort by price url and browse items sorted by price.
    
Given url 'http://localhost:8080/items/' 
And request {price: 20}
When method get
Then status 200
And print 'Response is ', response
And match response contains {id: '#notnull', price: '#? _ <= 20'}


