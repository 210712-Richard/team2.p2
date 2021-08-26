Feature: create a store
Scenario: send a post and create a store

Given url 'http://localhost:8080/users/TestStore'
And request { firstName : 'John', lastName : 'Doe', address : 'nope, kansas', userType : 'SELLER' , currency : '200'}
When method post
Then status 200
And match response contains { name: 'TestStore', currency : '200' }

