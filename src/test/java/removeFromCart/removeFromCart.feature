Feature: remove an item from the shopping cart
Scenario: as a customer, i can remove an item from my shopping cart

Background:
	* def signin = call read('classpath:login/login.feature')
	
Given url 'http://localhost:8080/users/quentin/shoppingcart/garbage'
And request { uuid : '42af36de-79a4-4a81-b640-16676f5a211e'}
And cookie SESSION = signin.sessionCookie
When method post
Then status 200
# Worth Noting: i couldn't figure out a way to make a variable for the old list length
# 							so this test only works if there's only one item in the list before it runs
And match response contains []
