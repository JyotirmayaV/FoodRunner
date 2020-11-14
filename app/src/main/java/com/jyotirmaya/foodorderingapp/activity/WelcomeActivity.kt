package com.jyotirmaya.foodorderingapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.jyotirmaya.foodorderingapp.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({
            startActivity(Intent(this@WelcomeActivity,LoginActivity::class.java))
            finish()
        },2000)
            //finish()
    }
    override fun onBackPressed()
    {
        finish()
    }
}
