package mumtaz.binar.challangechapterenam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*
import mumtaz.binar.challangechapterenam.model.GetAllFilm
import mumtaz.binar.challangechapterenam.model.GetAllFilmItem
import mumtaz.binar.challangechaptertujuh.R

class AdapterFilm (private val onclick : (GetAllFilmItem)->Unit) : RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    var datafilm : List<GetAllFilmItem>? = null

    fun setDataFilm(film: List<GetAllFilmItem>){
        this.datafilm = film
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFilm.ViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: AdapterFilm.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.gambar)

        holder.itemView.textjudul.text = datafilm!![position].title
        holder.itemView.textdirec.text = datafilm!![position].director
        holder.itemView.textrelease.text = datafilm!![position].releaseDate

        holder.itemView.card.setOnClickListener {
            onclick(datafilm!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }else{
            return datafilm!!.size
        }
    }

}