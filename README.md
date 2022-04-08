[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Revature-CashOverflow_CashOverflow-spring&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Revature-CashOverflow_CashOverflow-spring) [![Build Status](http://ec2-18-233-7-153.compute-1.amazonaws.com:8080/buildStatus/icon?job=cashoverflow-spring%2Fmain)](http://ec2-18-233-7-153.compute-1.amazonaws.com:8080/job/cashoverflow-spring/job/main/)

# CashOverflow Spring Boot Server

This is a Spring Boot stateless resource/authentication server. It is the back end of a full stack application, the front end being an Angular single page application. This README will guide you through setting up your local environment so the application may run.

## Setup

### Software

1. Maven
2. Java 8
3. Preferrably an IDE like STS/Eclipse, IntelliJ
4. DBeaver for intializing the database and running the SQL script

### Environment variables for running the application

You need 3 environment variables for the Spring Boot application to run

1. AWS_DB_ENDPOINT - should point to an AWS RDS instance running PostgreSQL on port 5432. It could also point to localhost if you're running your own DBMS.  
   Note: if you are running PostgreSQL on a different port or a different DBMS vendor altogether you will need to modify the application.properties file.
2. AWS_USERNAME - the username for the database
3. AWS_PASSWORD - the password for the database

You also need to make sure that in your DBMS you have a database called cashoverflow. Be sure to run the included SQL script in CashOverflow/src/main/resources to build the schema in your database. See the STARTUP.md for more detailed steps on setting up the development environment.  
Note that the script is intended for PostgreSQL so make changes if appropriate for your DBMS.

You should now be able to run the application locally via the Boot Dashboard or running the main method. Alternatively, you could package it with maven on the command line and run the jar.
