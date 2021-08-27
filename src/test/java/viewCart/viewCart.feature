Feature: view my shoppingCart
Scenario: login, then view shoppingCart

Background:
	* def signin = call read('classpath:login/login.feature')

Given url 'http://localhost:8080/users/quentin/shoppingCart'
And request { uuid : '42af36de-79a4-4a81-b640-16676f5a211e'}
And cookie SESSION = signin.sessionCookie
When method get
Then status 200
And match response contains [{uuid: '3435aef4-fbe2-4a7e-ae5c-61f066e5ff92', storename: '#notnull', price: '#notnull', name: '#notnull', category: '#ignore', picture: '#ignore'}]