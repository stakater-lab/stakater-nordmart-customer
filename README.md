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

## Dependencies

It requires following things to be installed:

* Java: ^8.0
* Cassandra DB

## APIs

- `POST /micro/customers`
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
        
- `PUT /micro/customers/:customerId`
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
        
- `GET /micro/customers`
    - Return all customers. The caller of this API must pass a valid OAuth token
    
- `GET /micro/customers/search`
    - Search customer by email. The caller of this API must pass a valid OAuth token


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
  KEYCLOAK_REALM="nordmart" \
  KEYCLOAK_URL="https://keycloak-security.DOMAIN:8180/auth/" \
  KEYCLOAK_CLIENT_BACKEND="stakater-nordmart-backend" \
  KEYCLOAK_CLIENT_SECRET_KEY="<concealed>" \
  docker-compose up
  ```