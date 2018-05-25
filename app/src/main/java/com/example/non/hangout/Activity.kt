package com.example.non.hangout

class Activity( val name: String,
                val descrp: String,
                val lat: Float,
                val long: Float) {

    override fun toString(): String {
        return name
    }

}