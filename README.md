# Task Manager REST API

## Pre-requisite
1. Java 11
1. Maven 3.6.0
1. MySQL 5.7.33

## Build Locally. Follow the steps one after the other.
&nbsp;1.Download dependency

2.Go to project directory

3.Build project with test:
> mvn clean package

4.Build project without test:
> mvn clean package -DskipTests

5.Running the project with the JAR:

## GET
	http://localhost:8080/task

## GET By ID
	http://localhost:8080/task/1

## POST
	http://localhost:8080/task

# PUT
	http://localhost:8080/task

# DELETE
	http://localhost:8080/task/1

# Requests


