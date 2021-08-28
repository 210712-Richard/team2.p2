Feature: add an item to my inventory as a Store
Scenario: login as a store, then add an existing item to my inventory

Background:
	* def signin = call read('classpath:loginStore/loginStore.feature')

Given url 'http://localhost:8080/stores/DemoStore2/inventory'
And request { uuid : 'fd1793b7-67b5-4cba-bdf4-3b53e1cb2e68'}
And cookie SESSION = signin.sessionCookie
When method put
Then status 200
And match each response contains [{uuid: 'fd1793b7-67b5-4cba-bdf4-3b53e1cb2e68'}]