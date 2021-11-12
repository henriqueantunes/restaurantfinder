package com.henrique.restaurantfinder.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.henrique.restaurantfinder.dtos.SearchParametersDTO
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
class RestaurantResourceControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testTopFiveRestaurantsFound() {
        mockMvc.post("/findRestaurants") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString({ })
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$", hasSize<Array<Any>>(5))
        }
    }

    @Test
    fun testOutOfRangeDistance() {
        val requestJson = SearchParametersDTO(null, null, 99, null, null)

        mockMvc.post("/findRestaurants") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(requestJson)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    fun testBestChineseRestaurants() {
        val requestJson = SearchParametersDTO(null, 5, null, null, "Chinese")

        mockMvc.post("/findRestaurants") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(requestJson)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("[{\"restaurantName\":\"Gusto Delicious\",\"rating\":5,\"distance\":3,\"price\":50,\"cuisine\":\"Chinese\"},{\"restaurantName\":\"Lane Tasty\",\"rating\":5,\"distance\":5,\"price\":35,\"cuisine\":\"Chinese\"},{\"restaurantName\":\"Tasteful Grill\",\"rating\":5,\"distance\":9,\"price\":10,\"cuisine\":\"Chinese\"}]") }
        }
    }

    @Test
    fun testAllCuisinesFound() {
        mockMvc.get("/getCuisines").andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$", hasSize<Array<Any>>(19))
        }
    }
}