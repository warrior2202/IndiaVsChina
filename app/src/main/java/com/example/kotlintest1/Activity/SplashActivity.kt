package com.example.kotlintest1.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintest1.Modals.CategoryModal
import com.example.kotlintest1.R
import com.example.kotlintest1.Rest.ApiClient
import com.example.kotlintest1.Rest.ApiInterface
import retrofit2.Call

class SplashActivity : AppCompatActivity() {
    var splash_timeot = 3000



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val abc = Handler()
        abc.postDelayed({
            val `in` = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(`in`)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, splash_timeot.toLong())
    }
    }
