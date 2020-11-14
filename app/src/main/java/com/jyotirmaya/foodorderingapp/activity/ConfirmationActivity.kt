package com.jyotirmaya.foodorderingapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jyotirmaya.foodorderingapp.R
import com.jyotirmaya.foodorderingapp.adapter.MyCartRecyclerAdapter
import com.jyotirmaya.foodorderingapp.database.OrderedItemsDatabase
import com.jyotirmaya.foodorderingapp.database.OrderedItemsEntity
import com.jyotirmaya.foodorderingapp.fragment.OrderHistoryFragment
import com.jyotirmaya.foodorderingapp.util.ConnectionManager
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ConfirmationActivity : AppCompatActivity() {

    lateinit var btnOK :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        btnOK = findViewById(R.id.btnOK)
        btnOK.setOnClickListener(){
            var intent = Intent(this@ConfirmationActivity,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed()
    {
        var intent = Intent(this@ConfirmationActivity,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
        finish()
    }
}
