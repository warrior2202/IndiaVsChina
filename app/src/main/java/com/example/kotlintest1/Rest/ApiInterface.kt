package com.example.kotlintest1.Rest

import com.example.kotlintest1.Modals.CategoryModal
import com.example.kotlintest1.Modals.ProductModal
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("category_list")
    fun getcategory():Call<List<CategoryModal>>

    @GET("product_list")
    fun getProductlist(@Query("category_id") id:String):Call<JsonArray>
}