# credit-market-loan-api

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

API [Spring Boot](http://projects.spring.io/spring-boot/) for Mercado Crédito loans.

## Requirements

For building and running the application you need:

- [JDK 17](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.8.6](https://maven.apache.org)

## Running the application locally

This application is packaged as a war which has Tomcat embedded. No server is necessary. You run it using the ```java -jar``` command.

* Clone this repository
* There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.business.creditmarketloanapi.CreditMarketLoanApiApplication` class from your IDE.
* You can build the project and run the tests by running
```shell
mvn clean package -DskipTests=true
```
* Once successfully built, you can run the service by one of these two methods:
```shell
java -jar target/credit-market-loan-api-0.0.1-SNAPSHOT.jar
```
* Or you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
mvn spring-boot:run
```
* Check the stdout to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2022-09-09 16:22:37.661  INFO 45628 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-09 16:22:37.679  INFO 45628 --- [  restartedMain] c.b.c.CreditMarketLoanApiApplication     : Started CreditMarketLoanApiApplication in 9.61 seconds (JVM running for 10.649)
```

## About the Service

The service is a REST service to request new loans from users and find out what is the value and volume of pending debt. It uses an in-memory database (H2) to store the data.

Here are some endpoints you can call:

### Create loan applications

```
POST /api/loan
Accept: application/json
Content-Type: application/json

{
    "amount": 1000,
    "term": 12,
    "user_id": 1
}

RESPONSE: HTTP 200
Content:

{
    "status": "OK",
    "response": {
        "loan_id": 3,
        "installment": 85.61
    }
}
```

### Get list of loans

```
GET /api/loan?from=2022-09-09&to=2022-09-09

RESPONSE: HTTP 200
Content:

{
    "status": "OK",
    "response": {
        "loanList": [
            {
                "id": 1,
                "amount": 1000.0,
                "term": 12,
                "rate": 0.15,
                "user_id": 1,
                "target": "NEW",
                "date": "2022-09-09 16:46:06"
            },
            {
                "id": 2,
                "amount": 1000.0,
                "term": 12,
                "rate": 0.15,
                "user_id": 2,
                "target": "NEW",
                "date": "2022-09-09 16:46:11"
            },
            {
                "id": 3,
                "amount": 1000.0,
                "term": 12,
                "rate": 0.05,
                "user_id": 16,
                "target": "PREMIUM",
                "date": "2022-09-09 16:46:17"
            }
        ],
        "pageNo": 1,
        "pageSize": 10,
        "totalElements": 3,
        "totalPages": 1,
        "isLast": true
    }
}
```

### Create a record of a payment made

```
POST /api/payment
Accept: application/json
Content-Type: application/json

{
    "loan_id": 1,
    "amount": 400
}

RESPONSE: HTTP 200
Content:

{
    "status": "OK",
    "response": {
        "id": 1,
        "loan_id": 1,
        "debt": 400.0
    }
}
```

### Get the pending debt of the loans

```
GET /api/debt?loanId=1&date=2022-09-09
GET /api/debt?target=NEW&date=2022-09-09
GET /api/debt?date=2022-09-09

RESPONSE: HTTP 200
Content:

{
    "status": "OK",
    "response": {
        "balance": 1800.0
    }
}
```

### To view your H2 in-memory database

To view and query the database you can browse to http://localhost:8080/h2-console. Default username is 'h2' with the same password. Make sure you disable this in your production profiles. For more, see https://goo.gl/U8m62X

## Deploying the application to Heroku

The easiest way to deploy the sample application to Heroku is to use the [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli):

```shell
heroku push origin main
```

Project deployed on Heroku: [Credit Market Loan API](https://credit-market-loan-api.herokuapp.com)

# Questions and Comments: juanalsa@gmail.com