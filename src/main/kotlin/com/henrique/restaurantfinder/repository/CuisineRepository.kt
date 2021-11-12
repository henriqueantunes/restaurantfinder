package com.henrique.restaurantfinder.repository

import com.henrique.restaurantfinder.beans.Cuisine
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Cuisine repository extending from JpaRepository
 *
 * @constructor Create empty Cuisine repository
 */
@Repository
interface CuisineRepository : JpaRepository<Cuisine, Int> {

}