package com.henrique.restaurantfinder.utils

import com.henrique.restaurantfinder.beans.Cuisine
import com.henrique.restaurantfinder.beans.Restaurant
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


object CSVUtil {
    var TYPE = "text/csv"

    fun hasCSVFormat(file: MultipartFile): Boolean {
        return TYPE == file.contentType
    }

    fun csvToRestaurants(): List<Restaurant> {
        try {
            val restaurants: MutableList<Restaurant> = ArrayList()
            getCsv("/csv/restaurants.csv") { csvParser ->
                val csvRecords: Iterable<CSVRecord> = csvParser.records
                for (csvRecord in csvRecords) {
                    val restaurant = Restaurant(
                        null,
                        csvRecord.get("name"),
                        csvRecord.get("customer_rating").toInt(),
                        csvRecord.get("distance").toInt(),
                        csvRecord.get("price").toInt(),
                        null,
                        csvRecord.get("cuisine_id").toInt()
                    )
                    restaurants.add(restaurant)
                }
            }
            return restaurants
        } catch (e: IOException) {
            throw RuntimeException("failed to parse CSV file: " + e.message)
        }
    }

    fun csvToCuisines(): List<Cuisine> {
        try {
            val cuisines: MutableList<Cuisine> = ArrayList()
            getCsv("/csv/cuisines.csv") { csvParser ->
                val csvRecords: Iterable<CSVRecord> = csvParser.records
                for (csvRecord in csvRecords) {
                    val cuisine = Cuisine(
                        csvRecord.get("id").toInt(),
                        csvRecord.get("name"),
                    )
                    cuisines.add(cuisine)
                }
            }
            return cuisines
        } catch (e: IOException) {
            throw RuntimeException("failed to parse CSV file: " + e.message)
        }
    }

    private fun getCsv(path: String, parserFunc: (CSVParser) -> Unit) {
        this::class.java.getResourceAsStream(path).bufferedReader().use { fileReader ->
            CSVParser(
                fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
            ).use {
                parserFunc(it)
            }
        }
    }
}