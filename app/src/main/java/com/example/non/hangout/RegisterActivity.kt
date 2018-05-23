package com.example.non.hangout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun onRegisClicked(view: View) {
        var username = regusername.text
        var password = regpassword.text
        var conpassword = conpassword.text

        if(!password.equals(conpassword)) {
            var builder = AlertDialog.Builder(this)
            builder.setMessage("Wrong confirmation password").setNegativeButton("Retry", null).create().show()
        } else {

        }

    }
}
