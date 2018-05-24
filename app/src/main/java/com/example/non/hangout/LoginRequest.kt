package com.example.non.hangout

import android.app.VoiceInteractor
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class LoginRequest : StringRequest {

    lateinit var params: HashMap<String, String>

    constructor(url:String,username:String, password:String, listener:Response.Listener<String>) :
            super(Request.Method.POST,url,listener,null){
        params = HashMap()
        params.put("username", username)
        params.put("password", password)
    }


    override fun getParams() : Map<String, String> {
        return params
    }
}