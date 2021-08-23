Feature: login as Quentin
Scenario: send a request and login successfully

Given url loginUrl
And request {username: 'Quentin'}
When method put
Then status 200
And match response contains { username: 'Quentin', currency: '#notnull' }
And match responseCookies contains { SESSION: '#notnull' }
And def sessionCookie = responseCookies.SESSION
