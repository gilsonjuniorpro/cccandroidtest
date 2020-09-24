package cccandroidtest.ca.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey
    val id: String? = null,
    val email: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val phone_number: String? = null
)