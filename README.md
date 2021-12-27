# ReadingIsGood

## Requirements and Tech Stack

Java 11, Spring, Spring Boot, Hibernate, JUnit and Intellij or Eclipse for development and run.

##ApiList

####Customer-Api
###### EndPoints

---
- /api/customer -> Get (Get Current User's Orders)
---
####Book-Api
###### EndPoints

---
- /api/book -> Get (Get All Books)
---
- /api/book -> Post (Create New Book)
---
- /api/book -> Put (Update Book)
---
####Auth-Api
###### EndPoints

---
- /api/auth/signin -> Post (Singin with user)
---
- /api/auth/signup -> Post (Create New User)
---
####Order-Api
###### EndPoints

---
- /api/order/{ID} -> Get (Get Order By Id)
---
- /api/order -> Get (Get All Orders By Date Interval)
---
- /api/order -> Post (Create New Order)
---
####Statistics-Api
###### EndPoints

---
- /api/statistics -> Get (Get Current User's Statistics)
---

## Testing Application

### Run Application

- mvn clean package
- docker-compose up --build

### Test Users:
- username: admin
- password: 123456
- role:admin
---
- username: user
- password: 123456
- role:user
---

This application has a swagger implementation for testing.
Just go to [Swagger Link](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/) after run the app.


## Postman Collection
You can use this link to access collections of API's -> https://www.getpostman.com/collections/72237e19d661d87569ac