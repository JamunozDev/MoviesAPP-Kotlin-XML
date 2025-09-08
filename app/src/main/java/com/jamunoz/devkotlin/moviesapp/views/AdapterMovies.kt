package com.jamunoz.devkotlin.moviesapp.views

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jamunoz.devkotlin.moviesapp.R
import com.jamunoz.devkotlin.moviesapp.core.Constants
import com.jamunoz.devkotlin.moviesapp.models.MovieModel

class AdapterMovies(
    val context: Context,
    var moviesList: List<MovieModel>
): RecyclerView.Adapter<AdapterMovies.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

        val cvMovie = itemView.findViewById(R.id.cvMovie) as CardView
        val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
        val tvInfo = itemView.findViewById(R.id.tvInfo) as TextView
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //val view = View.inflate(context, R.layout.item_rvmovie, parent)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rvmovie, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val movie = moviesList[position]

        Glide.with(context).load("${Constants.API_URL_IMAGE}${movie.image}").
        apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT)).
            into(holder.ivImage)

        holder.tvInfo.text = HtmlCompat.fromHtml(
            "<b>Title:</b> " + movie.title +
            "<br></b>Popularity:</b> " + movie.popularity +
            "<br></b>Rating:</b> " + movie.rating,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        //EVENTOS
        holder.cvMovie.setOnClickListener {
            showOverview(movie.title, movie.overview)
        }
    }

    private fun showOverview(title: String, overview: String){
        var builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(overview)
        builder.show()
    }

}