Feature: create an item as a Seller
Scenario: send a put request after being logged-in as Seller

Given url 'http://localhost:8080/store'
And def signin = call read('classpath:login/login.feature')
And request { name: 'ipad', storename: 'BestBuy', price: 600.0, category: 'TECH'}
And cookie SESSION = signin.sellerSessionCookie
When method post
Then status 200
And match response contains { uuid: '#notnull', name: '#notnull', storename: '#notnull', price: '#notnull', category: '#notnull'}