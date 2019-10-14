package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    var name: String,
    var url: String
) : Parcelable