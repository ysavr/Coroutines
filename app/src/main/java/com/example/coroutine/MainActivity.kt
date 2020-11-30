package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.model.Movie
import com.example.coroutine.model.MovieEntity
import com.example.coroutine.network.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var movie: MovieEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMovie()
    }

    private fun getMovie() {
        CoroutineScope(Main).launch {
            try {
                withContext(IO) {
                    movie = ApiClient.instance().getListMovie(ApiClient.API_KEY, ApiClient.LANGUAGE)
                }
                movie?.results?.let { showMovie(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showMovie(movie: List<Movie>) {
        val adapter = MovieAdapter(movie)
        rv_movie.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}