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

To run keycloak, execute the following command : 

```bash
docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_IMPORT=/tmp/realm.json -p 8180:8080 -v /c/Users/tmp:/tmp --name sso jboss/keycloak
```
- realm.json : the file that contains configuration for keycloak.
- KEYCLOAK_USER : super user for keycloak.
- KEYCLOAK_PASSWORD : admin password for keycloak.

For more information : https://hub.docker.com/r/jboss/keycloak


  
