# stakater-nordmart-customer

## Overview

A maven spring boot catalog application for the customer information retrieval.

## Dependencies

It requires following things to be installed:

* Java: ^8.0
* Cassandra DB

## Deployment strategy

### Local deployment

To run the application locally use the command given below:

```bash
mvn spring-boot:run
```

### Docker

To deploy app inside a docker container

* Create a network if it doesn't already exist by executing

  ```bash
  docker network create --driver bridge nordmart-apps
  ```

* Build jar file of the app by executing command given below:

  ```bash
  mvn clean package
  ```

* Next configure env variable in .env file

* Finally run the customer and cassandra image with docker compose

  ```bash
  docker-compose up
  ```