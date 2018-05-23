package com.example.non.hangout

import android.app.VoiceInteractor
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class RegisterRequest : StringRequest {

    lateinit var params: HashMap<String, String>

    constructor(url:String,username:String, password:String, listener:Response.Listener<String>) :
            super(Request.Method.POST,url,listener,null){
        params = hashMapOf("username" to username, "password" to password)
    }


    override fun getParams() : Map<String, String> {
        return params
    }
}