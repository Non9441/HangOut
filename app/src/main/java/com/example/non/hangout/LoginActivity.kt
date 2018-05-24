package com.example.non.hangout

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.android.volley.Response.Listener
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private val REQUEST_URL : String = "https://nonpu.000webhostapp.com/Login.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLoginClicked(view: View) {
        val uname = username.text.toString()
        val pword = password.text.toString()

        var listener = Listener<String> {
            @Override
            fun onResponse(reponse : String) {
                var jsonObject = JSONObject(reponse)
                var success = jsonObject.getBoolean("success")

                if(success) {
                    var username = jsonObject.getString("username")
                    var intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("username", username)

                    this.startActivity(intent)
                } else {
                    var builder = AlertDialog.Builder(this)
                    builder.setMessage("Login Failed").setNegativeButton("Retry", null).create().show()
                }

            }
        }

        var loginRequest = LoginRequest(REQUEST_URL,uname,pword,listener)
        var requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(loginRequest)
    }

    fun onRegisterClicked(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}