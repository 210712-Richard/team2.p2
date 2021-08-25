Feature: Login as a user and customer can view all the available items for sale.
Scenario: As a logged in user, send a get request to the get item url and browse all the items for sale.

Given url 'http://localhost:8080/items/'
And request {}
When method get
Then status 200
And print 'Response is: ', response
And match response contains { id: '#notnull' }