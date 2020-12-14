
## Authorization Server Oauth2  
  
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

> 1 step - Get Code (e.g. ms-users is the created realm on keycloack)
* http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/auth
```
http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/auth?client_id=resource-server-client-app&response_type=code&state=fj8o3n7bdy1op5&scope=openid profile&redirect_uri=http://localhost:8081/callback
```
>PARAMS
```
client_id : resource-server-client-app | Foi setado ao criar novo client app no keycloak
response_type: code
state: 2398t2ng9 | Random Value generate by client app
scope: openid profile  
redirect_uri: http://localhost:8081/callback
```
> 2 step - Post code

* http://localhost:8080/auth/realms/ms-users/protocol/openid-connect/token
> Body x-www-form-urlencoded
```
client_id : resource-server-client-app
grant_type: authorization_code
client_secret: 40503fc0-...
code: 86ca443f-91ad-458c-acca-141ac6fe5a85.5517370e-efca-4f54-a0bc-e68e17ceaa9b.99772a6d-c8fa-4a72-a9c0-24310c040934
redirect_uri: http://localhost:8081/callback
scope: profile
```
> AUTHORIZATION GRANT FLOW COMPLETE
----








