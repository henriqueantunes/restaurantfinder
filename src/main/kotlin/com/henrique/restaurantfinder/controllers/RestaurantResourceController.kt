package com.henrique.restaurantfinder.controllers

import com.henrique.restaurantfinder.beans.Cuisine
import com.henrique.restaurantfinder.beans.Restaurant
import com.henrique.restaurantfinder.dtos.CuisineDTO
import com.henrique.restaurantfinder.dtos.RestaurantDTO
import com.henrique.restaurantfinder.dtos.SearchParametersDTO
import com.henrique.restaurantfinder.service.CuisineService
import com.henrique.restaurantfinder.service.RestaurantService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer
import javax.validation.Valid

/**
 * Restaurant resource controller
 *
 * @property restaurantService
 * @property cuisineService
 * @constructor Create a RestaurantResourceController injecting the services
 */
@RestController
class RestaurantResourceController (val restaurantService: RestaurantService, val cuisineService: CuisineService) {

    /**
     * Get all cuisines and transform into DTO
     *
     * @return All the cuisines from service with only the name
     */
    @CrossOrigin()
    @GetMapping("/getCuisines")
    fun getCuisines(): List<CuisineDTO> {
        val result: MutableList<CuisineDTO> = ArrayList()

        cuisineService.findCuisines().forEach { cuisine ->
            result.add(CuisineDTO(cuisine.NAME))
        }

        return result
    }

    /**
     * Get restaurants and transform into DTO
     *
     * @param parameters Parameters to filter search (name, distance, price, rating and cuisine name)
     * @return Top 5 restaurants matching the search criterias
     */
    @CrossOrigin()
    @PostMapping("/findRestaurants")
    fun getRestaurants(@Valid @RequestBody parameters: SearchParametersDTO): List<RestaurantDTO> {
        val result: MutableList<RestaurantDTO> = ArrayList()

        restaurantService.findBySearchParameters(parameters).forEach { restaurant ->
            result.add(RestaurantDTO(restaurant.NAME, restaurant.CUSTOMER_RATING, restaurant.DISTANCE, restaurant.PRICE, restaurant.cuisine?.NAME))
        }

        return result
    }

    /**
     * Handle invalid requests
     *
     * @param exception Validation exception thrown by Spring validation annotation
     * @return Composed message with all validation errors in the format (fieldName: "errorMessage")
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: BindException): ResponseEntity<Map<String, String?>>? {
        val errors: MutableMap<String, String?> = HashMap()
        exception.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errors)
    }
}