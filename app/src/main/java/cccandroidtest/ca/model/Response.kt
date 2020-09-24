package cccandroidtest.ca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    val estimate: Estimate? = null,
    val person: Person? = null
): Parcelable{}