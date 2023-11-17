package com.example.and101_group5


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random
import com.codepath.asynchttpclient.AsyncHttpClient as AsyncHttpClient1

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokedexButton = findViewById<Button>(R.id.main_pokedex_button)

        pokedexButton.setOnClickListener {
            val Intent = Intent(this, pokedex_activity::class.java)
            startActivity(Intent)
        }
        val infoButton = findViewById<Button>(R.id.main_pokemoves_button)

        infoButton.setOnClickListener {
            val Intent = Intent(this, InfoActivity::class.java)
            startActivity(Intent)
        }
        val battleButton = findViewById<Button>(R.id.main_mockbattle_button)

        battleButton.setOnClickListener {
            val Intent = Intent(this, BattleActivity::class.java)
            startActivity(Intent)
        }


    }





}