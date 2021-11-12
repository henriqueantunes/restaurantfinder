package com.henrique.restaurantfinder

import com.henrique.restaurantfinder.repository.RestaurantRepository
import com.henrique.restaurantfinder.utils.CSVUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * Spring Boot main application class
 *
 */
@SpringBootApplication
@EnableJpaRepositories("com.henrique.*")
@ComponentScan(basePackages = [ "com.henrique.*" ])
@EntityScan("com.henrique.*")
class RestaurantfinderApplication

fun main(args: Array<String>) {
	runApplication<RestaurantfinderApplication>(*args)
}