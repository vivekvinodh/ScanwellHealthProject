package com.example.scanwellhealthproject.models

import com.google.gson.annotations.SerializedName

/**
 * Data class representing counts of badges
 *
 * @property bronze Int?
 * @property gold Int?
 * @property silver Int?
 * @constructor
 */
data class BadgeCounts(
    @SerializedName("bronze")
    val bronze: Int?,
    @SerializedName("gold")
    val gold: Int?,
    @SerializedName("silver")
    val silver: Int?
)