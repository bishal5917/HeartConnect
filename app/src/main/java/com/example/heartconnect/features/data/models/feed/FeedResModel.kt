package com.example.heartconnect.features.data.models.feed


import com.google.gson.annotations.SerializedName

class FeedResModel : ArrayList<FeedResModel.FeedResModelItem>(){
    data class FeedResModelItem(
        @SerializedName("birthYear")
        val birthYear: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("gender")
        val gender: String?,
        @SerializedName("hobbies")
        val hobbies: List<String?>?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("phone")
        val phone: String?
    )
}