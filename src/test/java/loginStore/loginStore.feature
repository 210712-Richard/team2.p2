Feature: login as DemoStore
Scenario: send a request and login successfully

Given url 'http://localhost:8080/stores'
And request { name: 'DemoStore'}
When method post
Then status 200
And match response contains { name: 'DemoStore', currency: '#notnull' }
And match responseCookies contains { SESSION: '#notnull' }
And def sessionCookie = responseCookies.SESSION