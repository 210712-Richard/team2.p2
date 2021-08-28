Feature: Login as a store, I can remove items from my inventory.
Scenario: As a logged Store user, I can request to remove an item from my inventory.

Background: 
	* def signin = call read('classpath:loginStore/loginStore.feature')
	* def addToInventory = call read('classpath:addToInventory/addToInventory.feature')

Given url 'http://localhost:8080/stores/DemoStore2/inventory/garbage'
And request {uuid: 'fd1793b7-67b5-4cba-bdf4-3b53e1cb2e68'}
And cookie SESSION = signin.sessionCookie
And cookie SESSION = addToInventory.sessionCookie
When method delete
Then status 200
And match response !contains [{uuid : 'fd1793b7-67b5-4cba-bdf4-3b53e1cb2e68', name : '#ignore', storename : '#ignore', price : '#ignore'}]