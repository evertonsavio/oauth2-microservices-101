### Spring Cloud Oauth2  
  
* http://www.mastertheboss.com/jboss-frameworks/keycloak/configuring-keycloak-database
  
``` 
docker network create keycloak-network
ocker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres
docker run --name keycloak --net keycloak-network jboss/keycloak -e DB_USER=keycloak -e DB_PASSWORD=password
```
