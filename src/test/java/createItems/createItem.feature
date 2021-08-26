Feature: create an item as a Seller
Scenario: send a put request after being logged-in as Seller

Given url 'http://localhost:8080/store'
And def signin = call read('classpath:login/login.feature')
And request { name: 'ipad', storeName: 'BestBuy', price: 600.0, itemType: 'TECH'}
And cookie SESSION = signin.sessionCookie
When method post
Then status 200
And match response contains { id: '#notnull', name: '#notnull', storeName: '#notnull', price: '#notnull', itemType: '#notnull'}