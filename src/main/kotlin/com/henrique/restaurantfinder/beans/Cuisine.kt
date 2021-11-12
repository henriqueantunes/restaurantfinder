package com.henrique.restaurantfinder.beans

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Cuisine
 *
 * @property ID unique Cuisine identifier
 * @property NAME Cuisine name
 * @constructor Create a Cuisine with an ID and a Name
 */
@Entity
@Table(name = "CUISINES")
data class Cuisine(@Id val ID: Int, val NAME: String)