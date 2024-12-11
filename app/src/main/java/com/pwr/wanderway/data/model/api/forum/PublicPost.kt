package com.pwr.wanderway.data.model.api.forum

import java.util.Date

data class PublicPost(
    val title: String,
    val body: String,
    val rating: Int,

    val id: Int,
    val route: Int,
    val author: String, //or other variable name for user nickname
    val created_at: Date,
    val random_poi_id: Int //lorem picsum or somehow related to route
    //or i can just fetch picture of route
)