package com.jamunoz.devkotlin.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamunoz.devkotlin.moviesapp.core.Constants
import com.jamunoz.devkotlin.moviesapp.models.MovieModel
import com.jamunoz.devkotlin.moviesapp.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel: ViewModel() {
    private var movielist = MutableLiveData<List<MovieModel>>()
    val moviesList: LiveData<List<MovieModel>> = movielist

    fun getAllMovies(){
        viewModelScope.launch( Dispatchers.IO) {
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    movielist.value = response.body()!!.results.sortedBy { it.title }
                }
            }
        }
    }

    fun getPopular(){
        viewModelScope.launch( Dispatchers.IO) {
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    movielist.value = response.body()!!.results.sortedByDescending { it.popularity.toFloat() }
                }
            }
        }
    }

    fun getTopRated(){
        viewModelScope.launch( Dispatchers.IO) {
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    movielist.value = response.body()!!.results.sortedByDescending { it.rating.toFloat() }
                }
            }
        }
    }


    fun getUpcoming(){
        viewModelScope.launch( Dispatchers.IO) {
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    movielist.value = response.body()!!.results.sortedBy { it.title }
                }
            }
        }
    }
}