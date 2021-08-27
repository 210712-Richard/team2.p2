Feature: Login as a user and customer can remove items from their wishlist.
Scenario: As a logged in user, send a put request and remove the item from wishlist.

Background: 
	* def signin = call read('classpath:login/login.feature')
	* def addToWishlist = call read('classpath:addToWishlist/addToWishlist.feature')

Given url 'http://localhost:8080/users/quentin/wishlist/garbage'
And request {uuid: '42af36de-79a4-4a81-b640-16676f5a211e'}
And cookie SESSION = signin.sessionCookie
And cookie SESSION = addToWishlist.sessionCookie
When method post
Then status 200
And match response !contains [{uuid : '42af36de-79a4-4a81-b640-16676f5a211e', name : '#ignore', storename : '#ignore', price : '#ignore'}]
