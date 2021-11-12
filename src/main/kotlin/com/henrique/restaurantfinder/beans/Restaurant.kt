package com.henrique.restaurantfinder.beans

import javax.persistence.*

/**
 * Restaurant
 *
 * @property id Unique Restaurant identifier (generated automatically)
 * @property NAME Name
 * @property CUSTOMER_RATING Average customer rating
 * @property DISTANCE Distance from user
 * @property PRICE Average price
 * @property cuisine Type of cuisine (reference to Cuisine model) (this is only used for fetching and is not tracked)
 * @property cuisineId Id from the referenced cuisine (this is used only for inserting a restaurant passing only the id)
 * @constructor Create a Restaurant with all the necessary properties
 */
@Entity
@Table(name = "RESTAURANTS")
data class Restaurant(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null, val NAME: String, val CUSTOMER_RATING: Int, val DISTANCE: Int, val PRICE: Int,
                      @JoinColumn(name = "cuisine_id", insertable = false, updatable = false)
                      @ManyToOne(fetch = FetchType.EAGER) val cuisine: Cuisine? = null,
                      @Column(name = "cuisine_id") val cuisineId: Int) : Comparable<Restaurant>
{

    /**
     * Compare to is based on the following factors
     * - Sort the restaurants by Distance first.
     * - After the above process, if two matches are still equal, then the restaurant with a higher customer rating wins.
     * - After the above process, if two matches are still equal, then the restaurant with a lower price wins.
     * - After the above process, if two matches are still equal, the one with alphabetically order first wins.
     *
     * @param other the Restaurant to be compared
     * @return 1 if bigger, -1 if smaller and 0 if equals
     */
    override fun compareTo(other: Restaurant): Int {
        return when {
            this.DISTANCE != other.DISTANCE -> {
                this.DISTANCE - other.DISTANCE
            }
            this.CUSTOMER_RATING != other.CUSTOMER_RATING -> {
                other.CUSTOMER_RATING - this.CUSTOMER_RATING
            }
            this.PRICE != other.PRICE -> {
                this.PRICE - other.PRICE
            }
            this.NAME != other.NAME -> {
                this.NAME.compareTo(other.NAME)
            }
            else -> 0
        }
    }
}