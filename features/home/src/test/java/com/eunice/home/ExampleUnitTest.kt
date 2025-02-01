package com.eunice.home

import com.eunice.domain.model.Category
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

private var categoryList = arrayListOf(
    Category("43527", "Beef",
        "https://www.themealdb.com/images/media/meals/1525873040.jpg", "Love it"),
    Category("43523", "Fish",
        "https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg", "Love it"),
    Category("43527", "Egg",
        "https://www.themealdb.com/images/media/meals/2dsltq1560461468.jpg", "Love it")
)