# Restaurant Finder Kotlin Backend

This project was created to exemplify a backend application using Kotlin and Spring Boot\
It uses Graddle for dependency management
It also has a Swagger service to document an Open API\

The app loads the CSVs in the resources folder into an in memory database at startup. And provides two URLs to query the results

`/findRestaurants`	 
For finding the restaurants based on some of the optional parameters in the JSON:
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

## Building and Running the APP

IntelliJ IDE provides great Graddle and Kotlin support. So a recommend using it to build and run the project in development mode

## Unit Testing
Unit testing was done using JUnit5 and Spring Boot.\
The tests mock the application and run some POSTs and GET requests to the API and assert the results