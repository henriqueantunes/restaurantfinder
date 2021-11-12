package com.henrique.restaurantfinder.config

import com.henrique.restaurantfinder.repository.CuisineRepository
import com.henrique.restaurantfinder.repository.RestaurantRepository
import com.henrique.restaurantfinder.utils.CSVUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

/**
 * Populate the database with Cuisine and Restaurant Data
 */
@Component
class ApplicationPopulate : ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    lateinit var restaurantRepo: RestaurantRepository

    @Autowired
    lateinit var cuisineRepo: CuisineRepository

    /**
     * Overrides applicationReady event to execute the populate method on startup
     *
     * @param event applicationReady event
     */
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        cuisineRepo.saveAll(CSVUtil.csvToCuisines())
        restaurantRepo.saveAll(CSVUtil.csvToRestaurants())
    }
}