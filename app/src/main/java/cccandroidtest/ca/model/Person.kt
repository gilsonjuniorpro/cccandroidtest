package cccandroidtest.ca.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Person(
    @PrimaryKey
    val id: String? = null,
    val email: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val phone_number: String? = null
): Parcelable {}