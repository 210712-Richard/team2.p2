Feature: create an account
Scenario: send a put and create an account

Given url 'http://localhost:8080/users/Quentin'
And request { firstName : 'John', lastName : 'Doe', email : 'johndoe@mail.com', address : 'nope, kansas' }
When method put
Then status 200
And match response contains { username: 'JDoe', firstName : 'John' }
