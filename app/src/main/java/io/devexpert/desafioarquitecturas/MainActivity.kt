package io.devexpert.desafioarquitecturas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import io.devexpert.desafioarquitecturas.data.MoviesRepository
import io.devexpert.desafioarquitecturas.data.local.LocalDataSource
import io.devexpert.desafioarquitecturas.data.local.MoviesDatabase
import io.devexpert.desafioarquitecturas.data.remote.RemoteDataSource
import io.devexpert.desafioarquitecturas.ui.screens.home.Home

class MainActivity : ComponentActivity() {

    private lateinit var db: MoviesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            MoviesDatabase::class.java, "movies-db"
        ).build()

        val repository = MoviesRepository(
            localDataSource = LocalDataSource(db.moviesDao()),
            remoteDataSource = RemoteDataSource()
        )

        setContent {
            Home(repository)
        }
    }
}