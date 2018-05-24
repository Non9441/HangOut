package com.example.non.hangout

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.android.volley.Response.Listener
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private val REQUEST_URL : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun onRegisClicked(view: View) {
        var username = regusername.text.toString()
        var password = regpassword.text.toString()
        var conpassword = conpassword.text.toString()

        if(!password.equals(conpassword)) {
            var builder = AlertDialog.Builder(this)
            builder.setMessage("Wrong confirmation password").setNegativeButton("Retry", null).create().show()
        } else {
            var listener = Listener<String> {
                @Override
                fun onResponse(reponse: String) {
                    var jsonObject = JSONObject(reponse)
                    var success = jsonObject.getBoolean("success")

                    if (success) {
                        var intent = Intent(this, LoginActivity::class.java)
                        this.startActivity(intent)
                    } else {
                        var builder = AlertDialog.Builder(this)
                        builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show()
                    }

                }
            }

            var registerRequest = RegisterRequest(REQUEST_URL,username,password,listener)
            var requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(registerRequest)
        }

    }
}
