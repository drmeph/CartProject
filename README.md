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
### Customers and Admins
2 types of users can access the api:
* Customers: unauthenticated users that can access only a subset of the api features. Public requests
* Admins: authentificated users and have access to the entire set of the api features. To login : 

> curl -i -X POST -d username=admin -d password=admin -c /tmp/cookies.txt http://[hostname]:[port]/login

### Products List:
A user can display the list of products available in store by requesting the 
* Customers: /catalog/list-products
  * will display the full list of product in the catalog with their name and identifiers
* Admins: /admin/catalog/list-products
  * will display the full list of product in the catalog with their name and the quantity in stock
  
### Display Product: /catalog/show-product/[productiId]
A customer can display the details of a product (name, rating, in stock and price).  

### Rate product: /catalog/rate-product
A customer can rate a product on a scale of 1 - 10

### Add/Remove product to cart
A customer can add or remove a product to the cart, this will lock the product from the inventory.
[LIMITATION] the api does not handle session timeout and by extension doesn't release the locked resource automatically, it needs to be done manually.
