# Restaurant Finder Kotlin Backend

This project was created to exemplify a backend application using Kotlin and Spring Boot\
It uses Gradle for dependency management
It also has a Swagger service to document an Open API\

The app loads the CSVs in the resources folder into an in memory database at startup. And provides two URLs to query the results

`/findRestaurants`	 
For finding the restaurants based on some optional parameters in the JSON:
```
{
  "restaurantName": "string",
  "customerRating": 0,
  "distance": 0,
  "price": 0,
  "cuisine": "string"
}
``` 

`/getCuisines`
Get all the cuisines available

### Live Demo
Azure deployed live [demo](https://restaurantfinder-kotlin.azurewebsites.net/swagger-ui.html) \
Since its hosted in a free tier Azure container, the application may take some time to startup

## Building and Running the APP

### Using IntelliJ
IntelliJ IDE provides great Gradle and Kotlin support. So I recommend using it to build and run the project in development.

### Docker support
Alternatively, gradlew and a Dockerfile are available in the project's root folder and it can be run with the following commands.

build the project jar file with:\
`gradlew bootJar`

create a docker image running:\
`docker build --build-arg JAR_FILE=build/libs/*.jar -t henrique/restaurantfinder .`

run the docker container:\
`docker run -p 8080:8080 henrique/restaurantfinder`

#### Docker Hub

You can also download the latest working image from my personal docker hub:\
`docker pull henriqueantuness/restaurantfinder:1.0.0`

And run it on docker:
`docker run -p 8080:8080 henriqueantuness/restaurantfinder:1.0.0`

### Swagger

When the app is running, acces the url:\
`http://localhost:8080/swagger-ui/`

Swagger creates an easy way to make API calls to the application, so you can run your tests.\
It also provides a documentation for the API, so you can see what parameters are necessary for each request.




## Unit Testing
Unit testing was done using JUnit5 and Spring Boot.\
The tests mock the application and run some POSTs and GET requests to the API and assert the results.