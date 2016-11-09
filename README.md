# Cart Project

## Introduction
Cart is a small project implementing a RESTFul api that provides the back-end support for a shopping cart. 
The project is implemented using Spring MVC, Spring Security, JDBC, MySQL, Tomcat, Gradle and liquibase

## Getting Started
### Prerequisites
* Java 8 Development Kit (JDK 8).
* Gradle 3.1.
* Tomcat 9 (just because :P).
* MySQL

### Installation
1. intall java + gradle, they must be in the PATH
2. Create a "cartproject" database in mysql
3. Create a "cpadmin" user in mysql and grant it access to "cartproject" database
4. At the root of the project run "gradle updatedatabase", it will initialise the database
5. At the root of the project run "gradle build", it will create the war file in the build folder
6. Place that war file in your tomcat's webapps folder
7. Run tomcat et voila!

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

## Limitations and possible improvements
### Anonymous users
Customers are anonymous users, no users are created, or need to be created for customers.

### No session timeout
Customers when requesting the back-end receive a jsessionid that the client can reuse to keep working with its cart. A possible improvement would be to add timeouts, that way when the session times out it will unlock the resource automatically.

### No Paging
When requesting the list of products, the full list is sent, which can become pretty big and slow down the response. A possible improvement is to implement paging, limiting each page to 25 to 50 records.

### No unique rate
Rating are done anonymously, which means that customers can send as many as they want. A possible improvement could be to map the jsessionid with the rating record, to ask them to add a piece of information (eg. email) or introduce a new role for authentificated customers. Customers that are authentified would be the only ones able to rate a product.

### No logout
For now logout isn't implemented.
