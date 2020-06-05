package com.example.kotlintest1.Activity

//import com.example.kotlintest1.Rest.ServiceBuilder
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest1.Adapter.MainAdapter
import com.example.kotlintest1.Modals.CategoryModal
import com.example.kotlintest1.R
import com.example.kotlintest1.RecyclerItemClickListener
import com.example.kotlintest1.Rest.ApiClient
import com.example.kotlintest1.Rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var pDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showProgDig()




        val request = ApiClient.buildService(ApiInterface::class.java)

//        val Category:List<CategoryModal>
        var category1:List<CategoryModal>
        var context: Context
        val RecycletTest:RecyclerView = findViewById(R.id.RecyclerViewMain)

        val call:Call<List<CategoryModal>> = request.getcategory()
        call.enqueue(object :Callback<List<CategoryModal>>{
            override fun onFailure(call: Call<List<CategoryModal>>, t: Throwable) {

                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<CategoryModal>>,
                response: Response<List<CategoryModal>>

            )
            {
                category1 = response.body()!!
                if (pDialog!!.isShowing){
                    pDialog!!.dismiss()
                }
                RecycletTest.layoutManager = GridLayoutManager(applicationContext,2)

                RecycletTest.adapter = MainAdapter(this@MainActivity,category1)
                RecycletTest!!.addOnItemTouchListener(
                    RecyclerItemClickListener(this@MainActivity, object : RecyclerItemClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
//                            Toast.makeText(this@MainActivity,"heya",Toast.LENGTH_LONG).show()
                            val goon = Intent(this@MainActivity, ComparisionActivity::class.java)
                            goon.putExtra("id",category1.get(position).id)
                            startActivity(goon)
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        }
                    }))


            }

        })




//        val call2: Call<JsonArray> = request.getProductlist("1")
//        call2.enqueue(object :Callback<JsonArray>{
//            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
//
//            }
//
//            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
//
//                response.body()?.get(0)
//                val jconfirmed = response.body()!!
//                val jsonElement1 = jconfirmed.asJsonArray
//                val indian_product_title = (jsonElement1.get(0) as JsonObject).get("indian_product_title")
//                val imageindia = (jsonElement1.get(0) as JsonObject).get("indian_product_images").asString
//                val imagechina = (jsonElement1.get(0) as JsonObject).get("other_country_product_images").asString
//                val other_title = (jsonElement1.get(0) as JsonObject).get("other_country_product_title")
//
//
//            }
//
//
//        })





    }

    private fun showProgDig() {
        pDialog = ProgressDialog(this)
        pDialog!!.setMessage("Please wait...")
        pDialog!!.setCancelable(true)
        pDialog!!.show()
    }

}

