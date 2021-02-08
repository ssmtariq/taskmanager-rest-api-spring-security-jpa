# Task Manager REST API

## Pre-requisite
1. Java 11
1. Maven 3.6.0
1. MySQL 5.7.33

## Build Locally by following the steps one after the another.
1. Download project
> https://github.com/ssmtariq/taskmanager-rest-api-spring-security-jpa.git

2. Open terminal and Go to project directory
> cd taskmanager-rest-api-spring-security-jpa/

3. Build project
> mvn clean install -DskipTests

4. Running the project with the JAR:
> java -jar target/taskmanager-rest-api-spring-security-jpa-0.0.1-SNAPSHOT.jar

## Prepare Demo
##### Prepare data for local demonstration using the following curl
	curl --location --request GET 'http://localhost:8080/prepare/demo' \
	--header 'Content-Type: application/json' \
	--data-raw ''

The above curl will create 2 **user** (1 ADMIN and 1 USER) as below-
User-1: username=admin1, password=12345, role=ADMIN
User-2: username=user1, password=1234, role=USER
You can generate **Authorization** header using the above credential form this [link](https://www.blitter.se/utils/basic-authentication-header-generator/)

Following headers are generated using the above credentials
Header for User1(ADMIN): **Authorization: Basic YWRtaW4xOjEyMzQ1**
Header for User2(USER): **Authorization: Basic dXNlcjE6MTIzNA==**
Apart from this, you can import curl in **postman** and input the above authorization credentials directly.
## Sample Requests
#### Create Project
	curl --location --request POST 'http://localhost:8080/project' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw '{
	    "name": "Add Integration Tests"
	}'

#### GET All Projects
	curl --location --request GET 'http://localhost:8080/projects' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw ''

#### Delete Project
	curl --location --request DELETE 'http://localhost:8080/project/109' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw ''

#### GET All Projects By User
##### Only accessible to ADMIN users
	curl --location --request GET 'http://localhost:8080/projects/user/98' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic YWRtaW4xOjEyMzQ1' \
	--data-raw ''

#### Create Task
	curl --location --request POST 'http://localhost:8080/task' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw '{
	    "description": "Design Data Flow Diagram",
	    "status": "OPEN",
	    "project": {
		"projectId": 100
	    },
	    "dueDate": "2021-01-31"
	}'

#### Edit Task
	curl --location --request PUT 'http://localhost:8080/task' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw '{
	    "taskId":102,
	    "description": "Navigation API Sequence Diagram Design",
	    "status": "closed",
	    "project": {
		"projectId": 100
	    },
	    "dueDate": "2021-02-07"
	}'

#### Get Task
	curl --location --request GET 'http://localhost:8080/task/101' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw ''

#### Get All Tasks By Project
	curl --location --request GET 'http://localhost:8080/tasks/project/100' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw ''

#### Get Expired Tasks
	curl --location --request GET 'http://localhost:8080/tasks/expired' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw ''

#### Get All Tasks By Status
	curl --location --request GET 'http://localhost:8080/tasks/status/open' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic dXNlcjE6MTIzNA==' \
	--data-raw ''

#### GET All Tasks By User
##### Only accessible to ADMIN users
	curl --location --request GET 'http://localhost:8080/tasks/user/98' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic YWRtaW4xOjEyMzQ1' \
	--data-raw ''

## Additional Requests
#### GET All Users
	curl --location --request GET 'http://localhost:8080/users' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic YWRtaW4xOjEyMzQ1' \
	--data-raw ''
