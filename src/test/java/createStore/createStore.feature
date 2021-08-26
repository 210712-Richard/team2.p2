Feature: create a store
Scenario: send a post and create a store

Given url 'http://localhost:8080/stores/TestStore'
And request { owner : 'John', currency : '200'}
When method post
Then status 200
And match response contains { name: 'TestStore', currency : '#notnull' }

