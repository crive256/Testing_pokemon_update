package com.example.and101_group5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokedexRecycler (private val petList: List<String>, private val petNameList: List<String>) : RecyclerView.Adapter<PokedexRecycler.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView
        val petName: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            petImage = view.findViewById(R.id.pokemon_image)
            petName = view.findViewById(R.id.pokemonName)
        }

    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokedex_images, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(petList[position])
            .centerCrop()
            .into(holder.petImage)

        holder.petName.text = petNameList[position]


    }
}