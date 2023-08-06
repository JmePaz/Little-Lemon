package com.jamesco.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.jamesco.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Dictionary

class MainActivity : ComponentActivity() {
    private val client: HttpClient = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType =  ContentType("text","plain"))
        }
    }
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "Little-lemon-db"
        ).build()
    }




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        lifecycleScope.launch{
            //read from network
            val menuNetwork = getMenuData()

            withContext(IO) {
                //database
                val menuDao = db.menuDao()

                //read
                val menuItems = menuDao.getMenuItems()
                val uniqueItems = mutableMapOf<Int, MenuItem>()
                for (item in menuItems) {
                    uniqueItems[item.id] = item
                }


                //write
                for (menuItemNetwork in menuNetwork.menu) {
                    val menuItem = menuItemNetwork.toMenuItem()
                    if(!uniqueItems.containsKey(menuItem.id)){
                        menuDao.insertMenuItem(menuItem)

                        Log.d("Saved: ", "Menu Item #${menuItem.id}")
                    }
                    else if (menuItem != uniqueItems[menuItem.id]){
                        menuDao.updateMenuItem(menuItem)
                        Log.d("Updated: ", "Menu Item #${menuItem.id}")
                    }
                    else{
                        Log.d("ALready saved:","Menu Item #${menuItem.id}")
                    }
                }


            }

        }

        setContent {
            LittleLemonTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Navigation(navController = rememberNavController())
                }
            }
        }
    }

    private suspend fun getMenuData(): MenuNetwork {
        return client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
             .body<MenuNetwork>()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}