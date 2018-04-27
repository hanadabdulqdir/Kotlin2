package com.example.hanad.greenflagassignment1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    //ImageButton imageButton2;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
    }

    fun onClick(view: View) {
        val myIntent = Intent(this, Main2Activity::class.java)
        startActivity(myIntent)
    }
}
