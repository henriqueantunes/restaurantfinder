package com.henrique.restaurantfinder.repository

import com.henrique.restaurantfinder.beans.Restaurant
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Restaurant repository extending from CrudRepository
 *
 * @constructor Create empty Restaurant repository
 */
@Repository
interface RestaurantRepository : CrudRepository<Restaurant, Int>{

    /**
     * Find all restaurants in database
     *
     * @return All restaurants from database
     */
    @Query("select r from Restaurant r")
    fun findRestaurants(): List<Restaurant>
}