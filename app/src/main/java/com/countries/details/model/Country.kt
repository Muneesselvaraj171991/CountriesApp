package com.countries.details.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Country : Parcelable {
    val capital: List<String>? = null
    val maps: Maps? = null
    val name: Name? = null
    val flags: Flags? = null
    val region: String? = null
   inner class Flags {
        val png: String? = null
    }
    inner class Maps {
        val googleMaps: String? = null
    }

    class Name {
        val common: String? = null
    }
}