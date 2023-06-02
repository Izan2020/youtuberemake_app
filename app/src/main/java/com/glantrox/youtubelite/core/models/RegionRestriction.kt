package com.glantrox.youtubelite.core.models


import com.google.gson.annotations.SerializedName

data class RegionRestriction(
    @SerializedName("blocked")
    val blocked: List<String>
)