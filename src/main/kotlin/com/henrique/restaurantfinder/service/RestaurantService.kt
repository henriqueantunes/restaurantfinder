package com.henrique.restaurantfinder.service

import com.henrique.restaurantfinder.beans.Restaurant
import com.henrique.restaurantfinder.dtos.SearchParametersDTO
import com.henrique.restaurantfinder.repository.RestaurantRepository
import org.springframework.stereotype.Service

/**
 * Restaurant service
 *
 * @property db RestaurantRepository to be injected
 * @constructor Create empty Restaurant service
 */
@Service
class RestaurantService(val db: RestaurantRepository) {

    /**
     * Find all restaurants from repository
     *
     * @return All restaurants from repository
     */
    fun findRestaurants(): List<Restaurant> = db.findRestaurants()

    /**
     * Find by search parameters defined (restaurant name, distance, price, rating and cuisine name)
     * if parameter is no present the field will not be filtered
     *
     * @param parameters Search parameters (restaurant name, distance, price, rating and cuisine name)
     * @return Top 5 restaurants filtered and sorted (see Restaurant compareTo for ordering factors)
     */
    fun findBySearchParameters(parameters: SearchParametersDTO): List<Restaurant> {
        return db.findRestaurants().filter {
            // Since compilers nowadays have optimizers to reduce the complexity of AND/OR operations
            // for the sake of readability the filter function is left separated by each parameter
            (if(parameters.distance != null) it.DISTANCE <= parameters.distance!! else true) &&
            (if(parameters.customerRating != null) it.CUSTOMER_RATING >= parameters.customerRating!! else true) &&
            (if(parameters.price != null) it.CUSTOMER_RATING <= parameters.price!! else true) &&
            (if(!parameters.cuisine.isNullOrBlank()) it.cuisine!!.NAME?.toLowerCase()
                .contains(parameters.cuisine!!.toLowerCase()) else true) &&
            (if(!parameters.restaurantName.isNullOrBlank()) it.NAME?.toLowerCase()
                .contains(parameters.restaurantName!!.toLowerCase()) else true)
        }.sorted().take(5)
    }
}