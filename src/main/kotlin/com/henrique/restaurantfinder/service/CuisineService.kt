package com.henrique.restaurantfinder.service

import com.henrique.restaurantfinder.beans.Cuisine
import com.henrique.restaurantfinder.repository.CuisineRepository
import org.springframework.stereotype.Service

/**
 * Cuisine service
 *
 * @property db CuisineRepository to be injected
 * @constructor Create empty Cuisine service
 */
@Service
class CuisineService(val db: CuisineRepository) {

    /**
     * Find all cuisines from repository
     *
     * @return All cuisines from repository
     */
    fun findCuisines(): List<Cuisine> = db.findAll()
}