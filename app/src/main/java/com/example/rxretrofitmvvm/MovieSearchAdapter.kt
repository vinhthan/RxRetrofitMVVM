package com.example.rxretrofitmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxretrofitmvvm.model.ResultMovie
import kotlinx.android.synthetic.main.item_movie_search.view.*
import java.util.ArrayList

class MovieSearchAdapter: RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder>() {

    var listMovies = ArrayList<ResultMovie>()

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var tvTitle = view.tv_title_movie
        private var imgMovie = view.img_movie
        fun bind(data: ResultMovie) {
            tvTitle.text = data.title
            Glide.with(imgMovie).load(data.getImagePosterPath()).into(imgMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}