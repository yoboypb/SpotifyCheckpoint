package com.example.checkpointmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checkpointmobile.model.Musica

class MusicaAdapter(private val musicList: List<Musica>) :
    RecyclerView.Adapter<MusicaAdapter.MusicaViewHolder>() {

    class MusicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return MusicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {
        val musica = musicList[position]
        holder.titleTextView.text = musica.titulo
        holder.descriptionTextView.text = musica.artista
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}
