Feature: add an item to my shoppingCart
Scenario: login, then the user adds an item to their shoppingCart

Background:
	* def signin = call read('classpath:login/login.feature')

Given url 'http://localhost:8080/users/quentin/shoppingCart'
And request { uuid : '42af36de-79a4-4a81-b640-16676f5a211e'}
And cookie SESSION = signin.sessionCookie
When method post
Then status 200
And match each response contains [{uuid: '42af36de-79a4-4a81-b640-16676f5a211e'}]