package com.example.rxretrofitmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxretrofitmvvm.model.MovieSearch
import com.example.rxretrofitmvvm.model.ResultMovie
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter: MovieSearchAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        search()
    }

    private fun search() {
        edt_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loadData(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun initRecyclerView() {
        rcy_movie_search.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter = MovieSearchAdapter()
            adapter = mAdapter
        }
    }

    private fun loadData(text: String) {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getMovieSearchObservable().observe(this, Observer<MovieSearch> {
            if (it != null) {
                mAdapter.listMovies = it.results as ArrayList<ResultMovie>
                mAdapter.notifyDataSetChanged()
            }else {

            }
        })
        viewModel.getMovieSearch("034bbd1b233d6726e0c7dc7f338657f9", text)
    }
}