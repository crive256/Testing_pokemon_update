package com.example.and101_group5

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class InfoActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_info)

        imageView = findViewById(R.id.pokemonImage)

        val searchPokemon = findViewById<Button>(R.id.searchPokemon)
        searchPokemon.setOnClickListener(){
            saveDetails()
        }






        val pokedexButton = findViewById<Button>(R.id.home)

        pokedexButton.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
        val infoButton = findViewById<Button>(R.id.pokedex)

        infoButton.setOnClickListener {
            val Intent = Intent(this, InfoActivity::class.java)
            startActivity(Intent)
        }
        val battleButton = findViewById<Button>(R.id.battle)

        battleButton.setOnClickListener {
            val Intent = Intent(this, pokedex_activity::class.java)
            startActivity(Intent)
        }
    }
    fun saveDetails(){
        val pokemonSearch = findViewById<EditText>(R.id.pokemonSearch)
        var userText = pokemonSearch.text.toString()

        val pokemonWeb = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$userText.png"

        Picasso.get().load("$pokemonWeb").placeholder(R.drawable.pokedex_replace)
            .error(R.drawable.pokedex_replace)
            .into(imageView)

    }



}