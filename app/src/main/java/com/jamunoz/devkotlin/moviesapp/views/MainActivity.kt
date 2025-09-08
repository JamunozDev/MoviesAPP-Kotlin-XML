package com.jamunoz.devkotlin.moviesapp.views

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jamunoz.devkotlin.moviesapp.R
import com.jamunoz.devkotlin.moviesapp.databinding.ActivityMainBinding
import com.jamunoz.devkotlin.moviesapp.viewmodels.MoviesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterMovies: AdapterMovies
    private lateinit var btnGetAll: Button
    private lateinit var btnGetPopular: Button
    private lateinit var btnGetTopRated: Button
    private lateinit var btnGetUpcoming: Button
    private lateinit var tvCategory: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enlaces de controles
        btnGetAll=findViewById(R.id.btnGetAll)
        btnGetPopular=findViewById(R.id.btnGetPopular)
        btnGetTopRated=findViewById(R.id.btnGetTopRated)
        btnGetUpcoming=findViewById(R.id.btnGetUpcoming)
        tvCategory=findViewById(R.id.tvCategory)

        // Inicialización del ViewModel
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        setupReciclerView()

        //Metodo por defecto
        tvCategory.text = "PREMIERE"
        viewModel.getAllMovies()

        //Observer y eventos
        viewModel.moviesList.observe(this){
            adapterMovies.moviesList = it
            adapterMovies.notifyDataSetChanged()

        }

        btnGetAll.setOnClickListener {
            tvCategory.text = "PREMIERE"
            viewModel.getAllMovies()
        }
        btnGetPopular.setOnClickListener {
            tvCategory.text = "POPULAR"
            viewModel.getPopular()
        }
        btnGetTopRated.setOnClickListener {
            tvCategory.text = "TOP RATED"
            viewModel.getTopRated()
        }
        btnGetUpcoming.setOnClickListener {
            tvCategory.text = "UPCOMING"
            viewModel.getUpcoming()
        }

    }

    private fun setupReciclerView() {
        var layoutManager= GridLayoutManager(this,3)
        binding.rvMovies.layoutManager=layoutManager
        adapterMovies= AdapterMovies(this,arrayListOf())

        binding.rvMovies.adapter=adapterMovies


    }
}