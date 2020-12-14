
### Spring Boot Resource Server Oauth2
 
> Resource Server initial dependencies
```
1. Spring Web
2. Spring Boot DevTools
3. Oauth2 Resource Server
```
* Application is secure now, need to provide Authorization Token on requests. For this work is necessary to provide 
the following properties to the application.
### Properties
> application.properties
```
#spring.security.oauth2.resourceserver.jwt.issuer-uri = http://localhost:8080/auth/realms/ms-users/
spring.security.oauth2.resourceserver.jwt.jwk-set-uri = http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/certs
```

