Feature: create an account
Scenario: send a put and create an account

Given url 'http://localhost:8080/users/Quentin'
And request { firstName : 'John', lastName : 'Doe', email : 'johndoe@mail.com', address : 'nope, kansas', userType : 'CUSTOMER', currency : '200'}
When method post
Then status 200
And match response contains { username: 'Quentin', firstName : 'John' }
