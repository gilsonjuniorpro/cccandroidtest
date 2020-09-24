package cccandroidtest.ca.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Estimate(
    @PrimaryKey
    val id: String? = null,
    val address: String? = null,
    val company: String? = null,
    val contact: String? = null,
    val created_by: String? = null,
    val created_date: String? = null,
    val number: Int = 0,
    val requested_by: String? = null,
    val revision_number: Int = 0
)