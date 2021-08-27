Feature: As a Seller I can browse items for sale in my store
Scenario: As a Seller I can view my inventory

Given url 'http://localhost:8080/stores/DemoStore2/items'
When method Get
Then status 200
And print 'Response is: ', response