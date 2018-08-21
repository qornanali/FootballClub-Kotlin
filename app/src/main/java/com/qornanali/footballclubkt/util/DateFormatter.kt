package com.qornanali.footballclubkt.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun toString(date: Date, format: String): String {
        return SimpleDateFormat(format).format(date)
    }

    fun toDate(date: String, format: String): Date {
        return SimpleDateFormat(format).parse(date)
    }

    fun now(): Long {
        return System.currentTimeMillis()
    }
}