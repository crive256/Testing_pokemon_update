package com.example.and101_group5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class BattleActivity : AppCompatActivity() {
    var petImageURL = ""
    var petImageURL2 = ""
    var pet1Type = ""
    var pet2Type = ""
    var pet3Type = 0
    var pet4Type = 0
    var petTypeImage1 = R.drawable.pokemon_type_icon_normal
    var petTypeImage2 = R.drawable.pokemon_type_icon_normal
    var petImageType1 = R.drawable.pokemon_type_icon_normal
    var petImageType2 = R.drawable.pokemon_type_icon_normal




    val array = arrayOf(
        arrayOf(0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0),
        arrayOf(0,1,1,0,2,2,0,0,0,0,0,2,1,0,1,0,2,0),
        arrayOf(0,2,1,0,1,0,0,0,2,0,0,0,2,0,1,0,2,0),
        arrayOf(0,0,2,1,1,0,0,0,1,2,0,0,0,0,1,0,0,0),
        arrayOf(0,1,2,0,1,0,0,1,2,1,0,1,2,0,1,0,1,0),
        arrayOf(0,1,1,0,2,1,0,0,2,2,0,0,0,0,2,0,1,0),
        arrayOf(2,0,0,0,0,2,0,1,0,1,1,1,2,1,0,2,2,1),
        arrayOf(0,0,0,0,2,0,0,1,1,0,0,0,1,1,0,0,1,2),
        arrayOf(0,2,0,2,1,0,0,2,0,1,0,1,2,0,0,0,2,0),
        arrayOf(0,0,1,1,2,0,2,0,0,0,0,2,1,0,0,0,1,0),
        arrayOf(0,0,0,0,0,0,2,2,0,0,1,0,0,0,0,1,1,0),
        arrayOf(0,1,0,0,2,0,1,1,0,1,2,0,0,1,0,2,1,1),
        arrayOf(0,2,0,0,0,2,1,0,1,2,0,2,0,0,0,0,1,0),
        arrayOf(1,0,0,0,0,0,0,0,0,0,2,0,0,2,0,1,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,1,1),
        arrayOf(0,0,0,0,0,0,1,0,0,0,2,0,0,2,0,1,0,1),
        arrayOf(0,1,1,1,0,2,0,0,0,0,0,0,2,0,0,0,1,2),
        arrayOf(0,1,0,0,0,0,2,1,0,0,0,0,0,0,2,2,1,0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle)
        val button = findViewById<Button>(R.id.petButton)
        val imageView = findViewById<ImageView>(R.id.petImage)
        val imageView2 = findViewById<ImageView>(R.id.petImage3)

        val pokedexButton = findViewById<Button>(R.id.home)

        pokedexButton.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
        val infoButton = findViewById<Button>(R.id.moves)

        infoButton.setOnClickListener {
            val Intent = Intent(this, InfoActivity::class.java)
            startActivity(Intent)
        }
        val battleButton = findViewById<Button>(R.id.pokedex_Battle)

        battleButton.setOnClickListener {
            val Intent = Intent(this, pokedex_activity::class.java)
            startActivity(Intent)
        }


        getDogImageURL()
        getDogImageURL2()
        getNextImage(button, imageView, imageView2)
    }



    private fun getDogImageURL() {
        val client = AsyncHttpClient()
        val choice = Random.nextInt(1000)
        val pokemon = "https://pokeapi.co/api/v2/pokemon/" + choice


        client[pokemon, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                pet1Type = json.jsonObject.getJSONArray("types").getJSONObject(0).
                getJSONObject("type").getString("name")
                petImageType1 = getTypeImage(pet1Type)
                pet3Type = getTypeID(pet1Type)
                petImageURL =
                    json.jsonObject.getJSONObject("sprites").getString("front_default")
                //json.jsonObject.getString("message")
                Log.d("Dog", "response successful")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }

    private fun getDogImageURL2() {
        val client = AsyncHttpClient()
        val choice = Random.nextInt(1000)
        val pokemon = "https://pokeapi.co/api/v2/pokemon/" + choice





        client[pokemon, object : JsonHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JSON
            ) {
                pet2Type = json.jsonObject.getJSONArray("types").getJSONObject(0).
                getJSONObject("type").getString("name")
                petImageType2 = getTypeImage(pet2Type)
                pet4Type = getTypeID(pet2Type)
                petImageURL2 = json.jsonObject.getJSONObject("sprites").getString("front_default")
                Log.d("Dog", "response successful")
            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]



        val winner = array[pet3Type][pet4Type]
        var name = "="
        if (winner == 1){
            name = "<"
        }
        else if (winner == 2){
            name = ">"
        }
        val textview = findViewById<TextView>(R.id.winner)
        textview.text = name


        val imageType1 = findViewById<ImageView>(R.id.type1)
        val imageType2 = findViewById<ImageView>(R.id.type2)

        imageType1.setImageResource(petImageType1);
        imageType2.setImageResource(petImageType2);

    }
    private fun getTypeImage(pet: String): Int{
        var petType = R.drawable.pokemon_type_icon_normal
        if (pet == "fire") {
            petType = R.drawable.pokemon_type_icon_fire
        } else if (pet == "water") {
            petType = R.drawable.pokemon_type_icon_water
        } else if (pet == "electric") {
            petType = R.drawable.pokemon_type_icon_electric
        } else if (pet == "grass") {
            petType = R.drawable.pokemon_type_icon_grass
        } else if (pet == "ice") {
            petType = R.drawable.pokemon_type_icon_ice
        } else if (pet == "fighting") {
            petType = R.drawable.pokemon_type_icon_fighting
        } else if (pet == "poison") {
            petType = R.drawable.pokemon_type_icon_poison
        } else if (pet == "ground") {
            petType = R.drawable.pokemon_type_icon_ground
        } else if (pet == "flying") {
            petType = R.drawable.pokemon_type_icon_flying
        } else if (pet == "psychic") {
            petType = R.drawable.pokemon_type_icon_psychic
        } else if (pet == "bug") {
            petType = R.drawable.pokemon_type_icon_bug
        } else if (pet == "rock") {
            petType = R.drawable.pokemon_type_icon_rock
        } else if (pet == "ghost") {
            petType = R.drawable.pokemon_type_icon_ghost
        } else if (pet == "dragon") {
            petType = R.drawable.pokemon_type_icon_dragon
        } else if (pet == "dark") {
            petType = R.drawable.pokemon_type_icon_dark
        } else if (pet == "steel") {
            petType = R.drawable.pokemon_type_icon_steel
        } else {
            petType = R.drawable.pokemon_type_icon_fairy
        }
        return petType
    }
    private fun getTypeID(pet: String): Int {
        var petType = 0
        if (pet == "fire") {
            petType = 1
        } else if (pet == "water") {
            petType = 2
        } else if (pet == "electric") {
            petType = 3
        } else if (pet == "grass") {
            petType = 4
        } else if (pet == "ice") {
            petType = 5
        } else if (pet == "fighting") {
            petType = 6
        } else if (pet == "poison") {
            petType = 7
        } else if (pet == "ground") {
            petType = 8
        } else if (pet == "flying") {
            petType = 9
        } else if (pet == "psychic") {
            petType = 10
        } else if (pet == "bug") {
            petType = 11
        } else if (pet == "rock") {
            petType = 12
        } else if (pet == "ghost") {
            petType = 13
        } else if (pet == "dragon") {
            petType = 14
        } else if (pet == "dark") {
            petType = 15
        } else if (pet == "steel") {
            petType = 16
        } else {
            petType = 17
        }
        return petType

    }

    private fun getNextImage(button: Button, imageView: ImageView, imageView2: ImageView) {
        button.setOnClickListener {
            getDogImageURL()
            getDogImageURL2()


            Glide.with(this)
                .load(petImageURL)
                .fitCenter()
                .into(imageView)

            Glide.with(this)
                .load(petImageURL2)
                .fitCenter()
                .into(imageView2)
        }

    }
}