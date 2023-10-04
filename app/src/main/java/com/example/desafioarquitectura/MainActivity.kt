package com.example.desafioarquitectura

import android.os.Bundle
import android.util.Log
import android.util.Log.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.desafioarquitectura.ui.theme.DesafioArquitecturaTheme
import com.example.desafioarquitectura.ui.theme.ResponseCharacters
import com.example.desafioarquitectura.ui.theme.Results
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DesafioArquitecturaTheme {
                val results = produceState<List<Results>>(initialValue = emptyList() ){
                    value = Retrofit.Builder()
                        .baseUrl("https://rickandmortyapi.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiServices::class.java)
                        .getCharactersAll("1")
                        .results
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) 
                {
                    //Greeting("Android")


                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = "Personajes") })}
                    ) { padding ->
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(120.dp),
                            modifier = Modifier.padding(padding)
                        )
                        {
                            items(results.value){
                                    result ->
                                Column {
                                    AsyncImage(
                                        model = result.image,
                                        contentDescription = result.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(2 / 3f)
                                            .padding(8.dp)
                                    )
                                    Text(
                                        text = result.name,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesafioArquitecturaTheme {
        Greeting("Android")
    }
}