Feature: add an item to my wishlist
Scenario: login, then the user adds an item to their wishlist

Background:
	* def signin = call read('classpath:login/login.feature')

Given url 'http://localhost:8080/users/quentin/wishlist'
And request { uuid : '3435aef4-fbe2-4a7e-ae5c-61f066e5ff92'}
And cookie SESSION = signin.sessionCookie
When method post
Then status 200
And match response contains [{uuid: '3435aef4-fbe2-4a7e-ae5c-61f066e5ff92', storename: '#notnull', price: '#notnull', name: '#notnull', category: '#ignore', picture: '#ignore'}]