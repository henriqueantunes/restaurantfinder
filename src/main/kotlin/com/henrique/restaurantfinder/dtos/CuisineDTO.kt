package com.henrique.restaurantfinder.dtos

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * Cuisine DTO to send throughout the API
 *
 * @property name Cuisine name
 * @constructor Create a CuisineDTO with a name
 */
@ApiModel(value = "Cuisine", description = "Cuisine name")
data class CuisineDTO(
    @ApiModelProperty(value = "Name of the cuisine type")
    var name: String?,
)