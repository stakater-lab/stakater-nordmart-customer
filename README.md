# stakater-nordmart-customer

## Overview

A maven spring boot application for the customer information retrieval.

Here is an overview of the project's features:

- Leverage Spring Boot framework to build a Microservices application.
- Uses Spring Data Cassandra to persist data to Cassandra database.
- Uses of embedded Cassandra for integration tests.
- Uses of Junit 5 for unit tests.
- Uses Cassandra as the customer database.
- Uses Docker to package application binary and its dependencies.

## User scenarios

**Customer profile operations**

Customer service provide operations to create, update, search customer profile: 
* Create - Customer profile can be created via nordmart web application.
* Update - Customer profile can be updated via nordmart web application.
* Search - Customer profile can be searched via nordmart web application.

## Configurations

Environment variables can be provided to configure the customer service.

**Keycloak configurations**
* KEYCLOAK_REALM - Keycloak realm name.
* KEYCLOAK_URL - Keycloak authentication URL.
* KEYCLOAK_CLIENT_BACKEND - Keycloak client for backend token based authentication.
* KEYCLOAK_CLIENT_SECRET_KEY - Keycloak secret key for accessing the keycloak client backend.

**Cassandra configuration configurations**
* CASSANDRA_HOST - Host database configuration.

## Dependencies

It requires following things to be installed:

* Java: ^8.0
* Cassandra DB

## APIs

- `POST /api/customers`
    - Create a customer. 
        - Return the saved customer.  The caller of this API must pass a valid OAuth token
    - Request body :
        ```
        {
          "email": "customer@gmail.com",
          "address": "8005 Rue de belleville",
          "gender": "M",
          "dateOfBirth": "2019-02-13",
          "phoneNumber": "147-852-5789"
        }`
        
- `PUT /api/customers/:customerId`
    - Update a customer. - Return the updated customer.  The caller of this API must pass a valid OAuth token
    - Request body :
        ```
        {
          "email": "customer@gmail.com",
          "address": "8005 Rue de belleville",
          "gender": "M",
          "dateOfBirth": "2019-02-13",
          "phoneNumber": "147-852-5789"
        }`
        
- `GET /api/customers`
    - Return all customers. The caller of this API must pass a valid OAuth token
    
- `GET /api/customers/search`
    - Search customer by email. The caller of this API must pass a valid OAuth token

- `GET /actuator/health`
    - Health check.

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

* Finally run the customer and cassandra image with docker compose

  ```bash
  KEYCLOAK_REALM=nordmart \
  KEYCLOAK_URL=https://keycloak-security.DOMAIN:8180/auth/ \
  KEYCLOAK_CLIENT_BACKEND=stakater-nordmart-backend \
  KEYCLOAK_CLIENT_SECRET_KEY=<concealed> \
  docker-compose up
  ```