Feature: change the category tag of an existing item
Scenario: as a seller, i can change the category tag of an item

Background:
	* def signin = call read('classpath:loginStore/loginStore.feature')
	
Given url 'http://localhost:8080/items/DemoStore/tags'
And request { uuid : '', category : 'PETS'}
And cookie SESSION = signin.sessionCookie
When method post
Then status 200
And match response contains { uuid : '', category : 'PETS' }