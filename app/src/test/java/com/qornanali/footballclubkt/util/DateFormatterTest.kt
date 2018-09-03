package com.qornanali.footballclubkt.util

import com.qornanali.footballclubkt.util.DateFormatter.formatToDate
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class DateFormatterTest {

    @Test
    fun testFormatToString() {
        val date = SimpleDateFormat("dd/MM/yyyy").format("3/8/2018")
        assertEquals("3/8/2018", formatToDate(date, "dd/MM/yyyy"))
    }

    @Test
    fun testFormatToDate() {
    }

    @Test
    fun testNow() {
    }
}