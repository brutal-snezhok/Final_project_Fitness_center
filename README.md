# Fitness center Web Application
---
###### This is a training project of the course "Java Web Development" on the topic "Fitness center".
### Starting the application
---
For starting the web application you should create a database connection and fill a database schema with data:
1. Change database access properties for your database server in src/main/resources/dbConfig.properties file.
2. Create a schema named "new_fitness_center" using src/main/resources/new_fitness_center.sql file.
### Used technologies
---
* Back-end: Java 8, Servlet, WebFilter, JSP, JSTL, Custom Tags.
* Database: JDBC, MySQL, self-written connection-pool.
* Front-end: HTML5, CSS3, JavaScript.
* Tests: TestNG.
* Other: Tomcat, Maven, Log4j2, Git, JavaDoc.
### Application architecture:
---
* **Client - Server** application architecture.
* **Layered architecture** where main backend layers are: Single FrontEnd Controller with Commands <-> Service Layer <-> DAO Layer <-> MySQL.
### Used Designed patterns:
---
* MVC
* Factory
* Controller
* Command
* DAO
* Proxy
* Singleton
 ### Application features
---
#### Common
* User role control.
* Multi-language user interface (Russian and English languages).
* Frontend and backend validation of input parameters.
#### Admin
* Login/logout.
* Edit/delete clients/trainers.
* Send messages from the mailbox of the fitness center.
* Delete reviews.
#### Trainer
* Registration.
* Login/logout.
* View trainer account information.
* Make an appointment to the client (food/exercises).
#### Client
* Registration.
* Login/logout.
* View client account information.
* Make order.
* Refuse to appoint a coach (food/exercises).
* Leave feedback about fitness center
