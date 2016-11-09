# Cart Project

## Introduction
Cart is a small project implementing a RESTFul api that provides the back-end support for a shopping cart. 
The project is implemented using Spring MVC, Spring Security, JDBC, MySQL, Tomcat, Gradle and liquibase

## Getting Started
### Prerequisites
> Java 8 Development Kit (JDK 8).
> Gradle 3.1.
> Tomcat 9 (just because :P).

## Key Features
### customers and admins
2 types of users can access the api:
* Customers: unauthenticated users that can access only a subset of the api features. Public requests
* Admins: authentificated users and have access to the entire set of the api features. To login : 

> curl -i -X POST -d username=admin -d password=admin -c /tmp/cookies.txt http://[hostname]:[port]/login
