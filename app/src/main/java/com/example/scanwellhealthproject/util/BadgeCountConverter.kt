package com.example.scanwellhealthproject.util

import androidx.room.TypeConverter
import com.example.scanwellhealthproject.models.BadgeCounts

class BadgeCountConverter {

    /**
     * Converter function to convert [BadgeCounts] object into comma separated [String]
     *
     * @param badgeCounts BadgeCounts
     * @return String
     */
    @TypeConverter
    fun badgeCountToString(badgeCounts: BadgeCounts): String {
        return badgeCounts.bronze.toString() + "," + badgeCounts.gold.toString() + "," + badgeCounts.silver.toString()
    }

    /**
     * Converter function to convert comma separated [String] into [BadgeCounts] object
     *
     * @param badgeCountString String
     * @return BadgeCounts
     */
    @TypeConverter
    fun stringToBadgeCounts(badgeCountString: String): BadgeCounts {
        val result = badgeCountString.split(",").map { it.trim() }
        return BadgeCounts(result[0].toInt(), result[1].toInt(), result[2].toInt())
    }
}