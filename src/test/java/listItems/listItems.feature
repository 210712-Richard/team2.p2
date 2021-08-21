Feature: As a Seller user, I can get a list of items for my store.
Scenario: As a Seller I can list items.

Given url 'https//localhost:8080/store/walmart/items'
When method get
Then status 200
And print 'Response is: ', response