### Keycloak SSL Configuration  
* https://github.com/maslick/keycloak-docker  

#### Gerando o certificado pelo LetsEncrypt  
* Verificando versao do SO:
```
lsb_release -a
```
* Instalando SNAP  
```
sudo snap install core; sudo snap refresh core
```
* Removendo instalações anteriores do certbot conforme documentação para Ubuntu 18
* https://certbot.eff.org/lets-encrypt/ubuntubionic-apache  
```
sudo apt-get remove certbot
```
* Instalando certbot  
```
sudo snap install --classic certbot
```
> Execute the following instruction on the command line on the machine to ensure that the certbot command can be run.
> sudo ln -s /snap/bin/certbot /usr/bin/certbot  

* Gerando o certificado no CA LetsEncrypt
```
sudo certbot certonly --standalone -d meudominio.com.br

IMPORTANT NOTES:
 - Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/meudominio.com.br/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/meudominio.com.br/privkey.pem
   Your cert will expire on 2021-03-28. To obtain a new or tweaked
   version of this certificate in the future, simply run certbot
   again. To non-interactively renew *all* of your certificates, run
   "certbot renew"
 - If you like Certbot, please consider supporting our work by:

   Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
   Donating to EFF:                    https://eff.org/donate-le
```
**Upar usuario para SUPER**
```
sudo -i
```
* As chaves foram geradas, acessar:
```
cd /etc/letsencrypt/live/meudominio.com.br/
```
* Executar
```
openssl pkcs12 -export -inkey privkey.pem -in fullchain.pem -out kc.pkcs12 -name meudominio.com.br  
keytool -importkeystore -deststorepass secret -destkeypass secret -destkeystore keycloak.jks -srckeystore kc.pkcs12 -srcstoretype PKCS12 -srcstorepass secret
```
* Copiar a keycloak.jks para o local onde o docker irá copia-la para o Container.
* Executar os scripts. REF: https://github.com/maslick/keycloak-docker 

#### Links utilizados

* (PRINCIPAL) https://github.com/maslick/keycloak-docker 
* (CERTBOT DOCS APACHE) https://certbot.eff.org/lets-encrypt/ubuntubionic-apache
* (PARCIAL DIRECT KEYSTORE) https://medium.com/@ajithprasadanandsadanam/quick-setup-of-ssl-certificates-using-certbot-on-keycloak-c74ac14760a
* (NÃO TESTADO) https://wjw465150.gitbooks.io/keycloak-documentation/content/server_installation/topics/network/https.html
---
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