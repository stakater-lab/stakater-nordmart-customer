# stakater-nordmart-keycloak

## Overview

A keycloak installation and configuration to secire nordmart application.

## Local deployment

Download the Keycloak Server: https://www.keycloak.org/downloads.html

Start keycloak : 

	- Linux : $ .../bin/standalone.sh.
	
	- Windows : > ...\bin\standalone.bat
	
For more information : https://www.keycloak.org/docs/latest/server_installation/

### Docker deployment

Pull the keycloak image within the following docker command :

```bash
docker pull jboss/keycloak
```

Replace `web-dev.DOMAIN` in the configuration file `realm-config.json` with the client web application domain.

To run keycloak, execute the following command : 

```bash
docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_IMPORT=/tmp/realm-config.json -p 8180:8080 -v /c/Users/tmp:/tmp --name sso jboss/keycloak
```
- realm-config.json : the file that contains configuration for keycloak.
- KEYCLOAK_USER : super user for keycloak.
- KEYCLOAK_PASSWORD : admin password for keycloak.

For more information : https://hub.docker.com/r/jboss/keycloak

## Configuration overview

The following configurations will be done when the file when the file realm-config.json is imported.
   - Realm name : Nordmart
   - Clients : 
     - stakater-nordmart-web : Client configuration for the web application authentication.
     - stakater-nordmart-backend : Client configuration for the backend services authentication.
     - Roles : admin, buyer, sales_manager, sales_staff.
   - Authentication flow : 
     - Cookie => ALTERNATIVE
     - Kerberos => DISABLED
     - Forms => ALTERNATIVE
     - Username Password Form => REQUIRED
     - OTP => DISABLED



  
