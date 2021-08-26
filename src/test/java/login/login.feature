Feature: login as quentin
Scenario: send a request and login successfully

Given url loginUrl
And request { username: 'ivan'}
When method post
Then status 200
And match response contains { username: 'ivan', userType: "CUSTOMER" }
And match responseCookies contains { SESSION: '#notnull' }
And def sessionCookie = responseCookies.SESSION

Scenario: send a request and login as SELLER

Given url loginUrl
And request { username: 'andres' }
When method post
Then status 200
And match response contains { username: 'andres', userType: "SELLER" }
And match responseCookies contains { SESSION: '#notnull' }
And def sellerSessionCookie = responseCookies.SESSION