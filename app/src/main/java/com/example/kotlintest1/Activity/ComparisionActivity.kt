package com.example.kotlintest1.Activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.kotlintest1.R
import com.example.kotlintest1.Rest.ApiClient
import com.example.kotlintest1.Rest.ApiInterface
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComparisionActivity : AppCompatActivity() {
    var pDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparision)
        val id:String = intent.getStringExtra("id")
        val request = ApiClient.buildService(ApiInterface::class.java)
        showProgDig()

        val call2: Call<JsonArray> = request.getProductlist(id)
        call2.enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                response.body()?.get(0)
                if (pDialog!!.isShowing){
                    pDialog!!.dismiss()
                }

                val jconfirmed = response.body()!!
                val jsonElement1 = jconfirmed.asJsonArray
                val indian_product_title = (jsonElement1.get(0) as JsonObject).get("indian_product_title")
                val imageindia = (jsonElement1.get(0) as JsonObject).get("indian_product_images").asString
                val imagechina = (jsonElement1.get(0) as JsonObject).get("other_country_product_images").asString
                val other_title = (jsonElement1.get(0) as JsonObject).get("other_country_product_title")
//                val indiatext:TextView = findViewById(R.id.IndiaTextView)
//                val othertext:TextView = findViewById(R.id.OtherContryTextView)
                val indiaimage:ImageView = findViewById(R.id.IndiaImageView)
                val Otherimage:ImageView = findViewById(R.id.OtherCountryImageView)

//                indiatext.setText(indian_product_title.asString)
//                othertext.setText(other_title.asString)
                Glide.with(this@ComparisionActivity).load(imageindia).into(indiaimage)
                Glide.with(this@ComparisionActivity).load(imagechina).into(Otherimage)


            }


        })

    }

    private fun showProgDig() {
        pDialog = ProgressDialog(this)
        pDialog!!.setMessage("Please wait...")
        pDialog!!.setCancelable(true)
        pDialog!!.show()
    }

}