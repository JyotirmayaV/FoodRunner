package com.jyotirmaya.foodorderingapp.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jyotirmaya.foodorderingapp.R
import com.jyotirmaya.foodorderingapp.util.ConnectionManager
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var etMobileNumber : EditText
    lateinit var etpassword : EditText
    lateinit var btnLogin : Button
    lateinit var txtRegister : TextView
    lateinit var txtForgot : TextView

    val validPassword = "jyotir"
    val User = "Jyotirmaya"
    val validMobileNumber = "9415266018"

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(
            getString(R.string.preference_file_name),
            Context.MODE_PRIVATE
        )

        val keyLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)



        var intent = Intent( this@LoginActivity , MainActivity::class.java )

        println("key logged in "+keyLoggedIn)
        if(keyLoggedIn)
            startActivity(intent)

        setContentView(R.layout.activity_login)
        //title = "Login"

        etMobileNumber = findViewById(R.id.etMobileNumber)
        etpassword = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtRegister = findViewById(R.id.txtRegister)
        txtForgot = findViewById(R.id.txtForgot)

        btnLogin.setOnClickListener {


            var MobileNumber = etMobileNumber.text.toString()
            var password = etpassword.text.toString()

            val queue = Volley.newRequestQueue(this@LoginActivity)

            val url = "http://13.235.250.119/v2/login/fetch_result"

            var username = "User"
            var emailaddress = "emailaddress"
            var location = "location"

            if(ConnectionManager().checkConnectivity(this@LoginActivity))
            {
                println("inside connection manager")
                val jsonParams = JSONObject()

                jsonParams.put("mobile_number",MobileNumber)
                jsonParams.put("password",password)

                println("paramneters are ready")
                val jsonObjectRequest = object : JsonObjectRequest(
                    Request.Method.POST, url, jsonParams,
                    Response.Listener {
                    println("Response is $it")
                    try {
                        println("inside try block")
                        val bdata = it.getJSONObject("data")
                        val success = bdata.getBoolean("success")
                        if (success) {
                            println("succcess")
                            val data = bdata.getJSONObject("data")

                            //start parsing data
                            val user_id = data.getString("user_id")
                            username = data.getString("name")
                            emailaddress = data.getString("email")
                            MobileNumber = data.getString("mobile_number")
                            location = data.getString("address")

                            println("name is ${username}")

                            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                            sharedPreferences.edit().putString("user_id", user_id).apply()
                            sharedPreferences.edit().putString("user_name", username).apply()
                            sharedPreferences.edit().putString("mobile_number", MobileNumber).apply()
                            sharedPreferences.edit().putString("email_id", emailaddress).apply()
                            sharedPreferences.edit().putString("location", location).apply()
                            startActivity(intent)

                        } //if success block
                        else {
                            //println("error")
                            val message = bdata.getString("errorMessage")
                            Toast.makeText(
                                this@LoginActivity,
                                message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }//else closing of if(success block implying data received correctly
                    }//try closing
                    catch (e: JSONException) {
                        Toast.makeText(
                            this@LoginActivity,
                            "JSONException error occured!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }//catch block closing
                },
                    Response.ErrorListener{
                        println("Response has some error and error is $it")
                        Toast.makeText(
                            this@LoginActivity,
                            "Volley error occured!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String,String>()
                        headers["Content-type"] = "application/json"
                        headers["token"] = "99d2334c9304fa"
                        return headers
                    }

                }
                println(jsonObjectRequest)
                println(jsonParams)
                queue.add(jsonObjectRequest)
            }//if closing of connection manager
            else
            {
                //val message = bdata.getString("errorMessage")
                val dialog = AlertDialog.Builder(this@LoginActivity)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection Not Found")
                dialog.setPositiveButton("Open Settings")
                {
                        text, listener -> val settingsIntent =  Intent(
                    Settings.ACTION_SETTINGS)
                    startActivity(settingsIntent)
                    finish()
                }
                dialog.setNegativeButton("Exit")
                {
                        text, listener -> finish()
                }
                dialog.create()
                dialog.show()
            }//else block of connection manager closing

        }//closing of when button clicked

            //println("the data is ${txtRegister.text}")
            txtRegister.setOnClickListener{

                Toast.makeText(this@LoginActivity , "Opening Registration Page" , Toast.LENGTH_LONG).show()
                intent = Intent( this@LoginActivity , RegistrationActivity::class.java )
                startActivity(intent)

            }

            txtForgot.setOnClickListener {
                Toast.makeText(this@LoginActivity , "Opening Forgot Password Page" , Toast.LENGTH_LONG).show()
                intent = Intent( this@LoginActivity , ForgotPassword1Activity::class.java )
                startActivity(intent)
            }

    }
    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onBackPressed() {
        finish()
    }
}
