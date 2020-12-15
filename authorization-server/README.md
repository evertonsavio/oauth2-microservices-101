
## Authorization Server Oauth2  

> ./standalone.sh -Djboss.socket.binding.port-offset=100  
  * User Storage SPI from image: 
```
FROM jboss/keycloak:10.0.1
COPY ./remote-postgres-spi.jar /opt/jboss/keycloak/standalone/deployments/   
```
  
> docker run -d -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin 09fff1d54e5f 
  
#### Static Users Configurations in a new Realm
> Create Realm > Create User > On Credentials tab:  set new password for the test user.
* Account profile:
> localhost:8080/auth/realms/NAME-OF-REALM/account
 ---
#### Client Application - Private
> Selecione Clients > Create > On Client ID: colocar o nome da applicação > Save
> Disable Direct Access Grants Type  
> Provide Temporary Valid Redirect URIs: http://localhost:8081/callback  
> Set Access Type to confidential  > Save
> On credential tab, copy Client Secret ID  

---
#### Authorization Code Grant Flow  

> 1 . GET step - Get Code (e.g. ms-users is the created realm on keycloack)
* http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/auth
```
http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/auth?client_id=resource-server-client-app&response_type=code&state=fj8o3n7bdy1op5&scope=openid profile&redirect_uri=http://localhost:8081/callback
```
>PARAMS
```
client_id : resource-server-client-app | Foi setado ao criar novo client app no keycloak
response_type: code
state: 2398t2ng9 | Random Value generate by client app
scope: openid profile offline_access
redirect_uri: http://localhost:8081/callback
```
> 2 . POST step - Post code

* http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/token
> Body x-www-form-urlencoded
```
client_id : resource-server-client-app
grant_type: authorization_code
client_secret: 40503fc0-...
code: 86ca443f-91ad-458c-acca-141ac6fe5a85.5517370e-efca-4f54-a0bc-e68e17ceaa9b.99772a6d-c8fa-4a72-a9c0-24310c040934
redirect_uri: http://localhost:8081/callback
scope: openid profile offline_access
```
> AUTHORIZATION GRANT FLOW COMPLETE
----
#### Refresh Token  
> 3 . POST  
```
 http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/token
 ```
> Body x-www-form-urlencoded
````
client_id : resource-server-client-app
grant_type: refresh_token
client_secret: 40503fc0-...
refresh_token: 2eg0win03...
````










