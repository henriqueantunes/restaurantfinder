package com.henrique.restaurantfinder.dtos

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min
import javax.validation.constraints.Max

/**
 * Search parameters that the API can receive to perform the restaurant search
 * null fields will be treated as properties not filtered
 *
 * @property restaurantName Name (or substring) of the restaurant
 * @property customerRating Minimum restaurant rating
 * @property distance Maximum distance from the restaurant
 * @property price Maximum restaurant price
 * @property cuisine Name (or substring) of the cuisine type
 * @constructor Create a SearchParametersDTO with all the necessary properties
 */
@ApiModel(value = "Parameters", description = "Parameters to search for a restaurant")
data class SearchParametersDTO(
    @ApiModelProperty(value = "Name (or substring) of the restaurant", required = false)
    var restaurantName: String?,

    @ApiModelProperty(value = "Minimum restaurant rating", required = false)
    @field:Min(1)
    @field:Max(5)
    var customerRating: Int?,

    @ApiModelProperty(value = "Maximum distance from the restaurant", required = false)
    @field:Min(1)
    @field:Max(10)
    var distance: Int?,

    @ApiModelProperty(value = "Maximum restaurant price", required = false)
    @field:Min(1)
    @field:Max(50)
    var price: Int?,

    @ApiModelProperty(value = "Name (or substring) of the cuisine type", required = false)
    var cuisine: String?
)