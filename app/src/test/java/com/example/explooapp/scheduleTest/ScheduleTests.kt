package com.example.explooapp.scheduleTest

import com.example.explooapp.ru.ui.UIKit.widgets.calendar.getOffsetFromDuration
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DurationOffsetTest {

    // Тест: корректный парсинг и вычисление для нескольких значений


    // Тест на часы с ведущим нулём
    @Test
    fun `should parse hour with leading zero correctly`() {
        assertEquals(12, getOffsetFromDuration("05:00")) // hours=5
    }


}