package com.example.scanwellhealthproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a user
 *
 * @property acceptRate Int?
 * @property accountId Int?
 * @property badgeCounts BadgeCounts?
 * @property creationDate Int?
 * @property displayName String?
 * @property isEmployee Boolean?
 * @property lastAccessDate Int?
 * @property lastModifiedDate Int?
 * @property link String?
 * @property location String?
 * @property profileImage String?
 * @property reputation Int?
 * @property reputationChangeDay Int?
 * @property reputationChangeMonth Int?
 * @property reputationChangeQuarter Int?
 * @property reputationChangeWeek Int?
 * @property reputationChangeYear Int?
 * @property userId Int?
 * @property userType String?
 * @property websiteUrl String?
 * @constructor
 */
@Entity(tableName = "user_table")
data class User(
    @SerializedName("accept_rate")
    val acceptRate: Int?,
    @PrimaryKey @SerializedName("account_id")
    val accountId: Int?,
    @SerializedName("badge_counts")
    val badgeCounts: BadgeCounts?,
    @SerializedName("creation_date")
    val creationDate: Int?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("is_employee")
    val isEmployee: Boolean?,
    @SerializedName("last_access_date")
    val lastAccessDate: Int?,
    @SerializedName("last_modified_date")
    val lastModifiedDate: Int?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @ColumnInfo @SerializedName("reputation")
    val reputation: Int?,
    @SerializedName("reputation_change_day")
    val reputationChangeDay: Int?,
    @SerializedName("reputation_change_month")
    val reputationChangeMonth: Int?,
    @SerializedName("reputation_change_quarter")
    val reputationChangeQuarter: Int?,
    @SerializedName("reputation_change_week")
    val reputationChangeWeek: Int?,
    @SerializedName("reputation_change_year")
    val reputationChangeYear: Int?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("user_type")
    val userType: String?,
    @SerializedName("website_url")
    val websiteUrl: String?
)