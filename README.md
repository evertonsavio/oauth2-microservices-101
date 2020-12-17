
### Spring Cloud Oauth2
  
* Debian quick notes
```
sudo chmod -R 777 ./
sudo chmod +x filename
```
* Databases  
```
https://www.tecmint.com/install-postgresql-and-pgadmin-in-ubuntu/
https://stackoverflow.com/questions/5598517/find-the-host-name-and-port-using-psql-commands#:~:text=The%20postgresql%20port%20is%20defined,conf%20file.&text=Changing%20the%20number%20there%20requires,for%20it%20to%20take%20effect.&text=You%20can%20use%20the%20command,%22%20at%20port%20%22port_number%22.
```
  
* Inicialização do keycloak e postgres por docker criando uma rede externa. 
``` 
docker network create --driver bridge postgres-network
docker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres
docker run --name keycloak --net keycloak-network jboss/keycloak -e DB_USER=keycloak -e DB_PASSWORD=password
```
* Linha de comando do container: 
```
docker exec -it <CONTAINER_ID> bash
```

### Links
* http://www.mastertheboss.com/jboss-frameworks/keycloak/configuring-keycloak-database

* https://www.appsdeveloperblog.com/keycloak-rest-api-create-a-new-user  
* https://www.ory.sh/oauth2-for-mobile-app-spa-browser/  
* https://appauth.io/
  
* https://renatogroffe.medium.com/postgresql-docker-executando-uma-inst%C3%A2ncia-e-o-pgadmin-4-a-partir-de-containers-ad783e85b1a4

-------------------------
### Passos para configuração do pg-admin após execução do docker-compose
  
* criar network com docker:


> docker network create keycloak-network

*  Verificar no docker-compose as configuracoes de network, ou ela gerará uma network default caso não adicionado: 
```
networks:
  default:
    external:
      name: keycloak-network
```

* Baixar a imagem do pg-admin  
> docker pull dpage/pgadmin4

``` 
docker run --name teste-pgadmin --network=keycloak-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=everluca@hotmail.com" -e "PGADMIN_DEFAULT_PASSWORD=PgAdmin2020!" -d dpage/pgadmin4
```

* Abrir o pgadmin em:
> http://localhost:15432 
 * Colocar o email e senha passados na execução da imagem
 * Executar os passos, clicar com botão direito em <b> Servers </b>
 > create > Server  
* Na aba General preencher Name com o nome da conexão e em Comments descrever a conexão.
* Na aba Connection preencher o Host name/address com o nome do container do postgres gerado pelo docker-compose nesse caso.
* Em Port deixar padrao 5432
* Username e Password do bando de dados descritos inicialmente na docker-compose.yml file.

 ### SMTP SERVER OAUTH2 KEYCLOAK CONFIGURATION
   
```
Host smtp.gmail.com
Port 465
From test.noreply@gmail.com
Enable SSL ON
Enable Authentication ON

```