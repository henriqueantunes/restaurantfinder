package com.henrique.restaurantfinder.dtos

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * Cuisine DTO to send throughout the API
 *
 * @property restaurantName Name of the restaurant
 * @property rating Restaurant rating (1 to 5)
 * @property distance Restaurant distance (1 to 10)
 * @property price Restaurant average price (1 to 50)
 * @property cuisine Restaurant cuisine type
 * @constructor Create a RestaurantDTO with all the necessary properties
 */
@ApiModel(value = "Restaurant", description = "Restaurant name to send on response")
data class RestaurantDTO(
    @ApiModelProperty(value = "Name of the restaurant")
    var restaurantName: String?,

    @ApiModelProperty(value = "Restaurant rating (1 to 5)")
    var rating: Int?,

    @ApiModelProperty(value = "Restaurant distance (1 to 10)")
    var distance: Int?,

    @ApiModelProperty(value = "Restaurant average price (1 to 50)")
    var price: Int?,

    @ApiModelProperty(value = "Restaurant cuisine type")
    var cuisine: String?
)