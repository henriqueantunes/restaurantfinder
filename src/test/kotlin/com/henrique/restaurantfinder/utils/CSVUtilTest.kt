package com.henrique.restaurantfinder.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CSVUtilTest {

    @Test
    fun testLoadRightRestaurantsCsv()
    {
        assertThat(CSVUtil.csvToRestaurants().size).isEqualTo(200)
    }

    @Test
    fun testLoadRightCuisinesCsv()
    {
        assertThat(CSVUtil.csvToCuisines().size).isEqualTo(19)
    }
}