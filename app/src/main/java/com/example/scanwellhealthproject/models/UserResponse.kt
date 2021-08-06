package com.example.scanwellhealthproject.models
import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response
 * @property hasMore Boolean?
 * @property items List<User>?
 * @property quotaMax Int?
 * @property quotaRemaining Int?
 * @constructor
 */
data class UserResponse(
    @SerializedName("has_more")
    val hasMore: Boolean?,
    @SerializedName("items")
    val items: List<User>?,
    @SerializedName("quota_max")
    val quotaMax: Int?,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int?
)