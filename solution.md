# Hreem IT Assessment

## Technologies
- Java 11
- Spring Boot 2.1.4
- Hibernate
- MySQL 8.0.29
- JUnit 4.12

## Development & Test Environment
- Ubuntu 22.04
- Java (Openjdk 11.0.15)
- Tomcat 9.0.31

## Production Environment
- Ubuntu 22.04 (on AWS-EC2 Instance)
- Java (Openjdk 11.0.15)
- Tomcat 9.0.31

## Technology Selection
In this project I prefer Java, because Java is a robust and well-known programming language. It has great libraries to extend abilities of the product. It provides elegant debugging system that helps to track any data flow. In addition Java is frontier of OOP paradigm. In this project I wrote interfaces and abstract classes for entities, services and API's. Thus each implementations of these classes will have ability to general CRUD operations with minimum coding. So the structure fits with open-close rule of SOLID. 

Spring Boot is an easy to use, well-structured framework. It helped me to develop REST API easily. Furthermore, Spring provides wide range of products in its ecosystem such as Spring Security, S. Cloud, S. Data etc. 

I used MySQL, because I am familiar with it. It is capable of managing millions of data.

Hibernate is a JPA implemantation. It helps developer to not spare time with SQL, instead it uses object relational mapping for developer. Hence developer just focuses on his/her Java objects. I used this time-saver for ORM operations.

I knew JUnit before. I get a course about it but I wouldn't use it in my projects. In this assessment I tried it without any mocking library.

Tomcat is a widely used application server for Java projects. It is easy to manage. Deployment process needs few effort in Tomcat.

I didn't used AWS before, but it is famous in it sector, so I chose it. I launched an EC2 instance with Ubuntu. It is known as a stable Linux distro. I use Ubuntu for ~10 years, so I am familiar with its environment.

For documentation, I used Javadoc. In my IDE (Netbeans) it is easy to generate Javadoc as a website, if developer writes its notes rely on rules. I wrote some parts of Javadoc and I released it at [this link](http://54.235.54.206/hreem-javadoc).

## REST API
The REST API runs at [this link](http://54.235.54.206:8080/hreem).
**Note: In case of any problem such as URL changes, please contact me via [this link](mailto:a.rahmanakkus@hotmail.com)**

### Endpoints

|       ROLE      |TYPE/LINK                          |PARAMS                         |
|----------------|-------------------------------|-----------------------------|
|Gets All Vocid Data|`[GET]` [`/vocid`](http://54.235.54.206:8080/hreem/vocid)            |            |
Gets All Vocid Data Which Has A Selected Symptom (i.e. cold)         |`[GET]` [`/vocid/symptom/{symptom}`](http://54.235.54.206:8080/hreem/vocid/symptom/cold)            |         |
|Gets All Vocid Data Which Has A Selected Hospital (i.e. konya)         |`[GET]` [`/vocid/hospital/{hospital}`](http://54.235.54.206:8080/hreem/vocid/hospital/konya) |
|Add New Vocid Data|`[POST]` [`/vocid`](http://54.235.54.206:8080/hreem/vocid)| ```json {"ssn": 100,"name": "Abdurrahman","surname": "AKKUŞ","age": 31,"symptoms": "cold","hospital": "istanbul"}``` 
|Update A Vocid Datum|`[PUT]` [`/vocid/{id}`](http://54.235.54.206:8080/hreem/vocid/1)|```json {"id": 1, "ssn": 100,"name": "Abdurrahman","surname": "AKKUŞ","age": 31,"symptoms": "cold","hospital": "istanbul"}``` 
|Delete A Vocid Datum|`[DELETE]` [`/vocid/{id}`](http://54.235.54.206:8080/hreem/vocid/1)|

