package cccandroidtest.ca.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Estimate(
    @PrimaryKey
    val id: String,
    val address: String? = null,
    val company: String? = null,
    val contact: String? = null,
    val created_by: String? = null,
    val created_date: String? = null,
    val number: Int = 0,
    val requested_by: String? = null,
    val revision_number: Int = 0
): Parcelable {

}