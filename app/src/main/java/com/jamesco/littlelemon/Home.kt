package com.jamesco.littlelemon

import android.util.Log
import android.view.Menu
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.get
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jamesco.littlelemon.ui.theme.Primary1
import com.jamesco.littlelemon.ui.theme.Secondary3
import com.jamesco.littlelemon.ui.theme.karlaTypography
import com.jamesco.littlelemon.ui.theme.marzakiTypography
import io.ktor.http.*
import java.util.*


@Composable
fun Home(navController: NavHostController){
    val context = LocalContext.current
    val db by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Little-lemon-db"
        ).build()
    }
    var filterList by remember{
        mutableStateOf(listOf<MenuItem>())
    }
    var categories by remember {
        mutableStateOf(listOf<String>())
    }
    val menuItemList by db.menuDao().getLiveMenuItems().observeAsState(listOf()).also {

        categories = it.value.groupBy { menuItem -> menuItem.category }.keys.toList()
            .map{it.replaceFirstChar {
                it.titlecase(Locale.getDefault())
            }}.plus("Sides")

    }

    var searchPhrase by remember{
        mutableStateOf("")
    }

    var selectedCategory by remember{
        mutableStateOf("")
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(0.7f)
            .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
             verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.weight(1f) )
            Image(painter = painterResource(R.drawable.logo),
                "Little Lemon Logo",
            modifier = Modifier
                .weight(2f, true)
                .requiredHeightIn(min = 100.dp),
            contentScale = ContentScale.FillWidth)
            Image(painterResource( R.drawable.profile),
                "user profile",
            modifier = Modifier
                .weight(1f)
                .size(65.dp)
                .clickable {
                    //go to profile
                    navController.navigate(Profile.route)
                },
            contentScale = ContentScale.Fit)
        }

        Surface(color = Primary1,
        modifier = Modifier
            .fillMaxWidth()
            .weight(2.4f)

            ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                Text("Little Lemon", style = marzakiTypography.h1,
                fontSize = 48.sp)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .weight(2f)){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(end = 10.dp)){
                        Text("Chicago", style = marzakiTypography.subtitle1)
                        Text("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist."
                        , style = karlaTypography.body2,
                        fontSize = 15.sp, lineHeight = 20.sp)

                    }
                    Image(painterResource(id = R.drawable.hero_img),"A hero Image",
                        modifier = Modifier
                            .size(130.dp, 150.dp)
                            .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop)

                }
                OutlinedTextField(value = searchPhrase,
                    onValueChange = {
                        searchPhrase = it

                    },
                leadingIcon = {Icon(Icons.Default.Search,"Search Icon")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 0.dp)
                    .weight(0.8f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = {Text("Enter Search Phrase",)}
                    )
            }

        }

        Column(modifier = Modifier
            .weight(1f)
            .padding(horizontal = 10.dp)){
            Spacer(modifier = Modifier.weight(0.2f))
            Text("ORDER FOR DELIVERY!",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(start = 10.dp),
            style = karlaTypography.subtitle1)
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            horizontalArrangement = Arrangement.SpaceAround) {
                items(
                  items = categories,
                  itemContent = { category ->
                      CategoryButton(category = category,
                          selectedCategory==category
                      ) {
                          selectedCategory = if(selectedCategory==category) "" else category
                      }
                  }
                )
            }
            Spacer(modifier = Modifier.weight(0.2f))
            Divider(color = Secondary3, thickness = 2.dp)
        }

        //filtering process
        filterList = when{
            searchPhrase.trim()=="" && selectedCategory!=""->{
                menuItemList.filter {
                    it.category.equals(selectedCategory, ignoreCase = true) }
            }
            searchPhrase.trim()!=""&&selectedCategory==""->{
                menuItemList.filter { menuItem -> menuItem.title
                    .contains(searchPhrase, ignoreCase = true) }

            }
            searchPhrase.trim()!=""&& selectedCategory!=""->{
                menuItemList.filter { it.category.equals(selectedCategory, ignoreCase = true) }
                    .filter {
                            menuItem -> menuItem.title
                        .contains(searchPhrase, ignoreCase = true)
                    }
            }
            else->{
                menuItemList
            }
        }

        //UI

        if(filterList.isEmpty()){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .weight(3f, fill = true),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Icon(Icons.Default.Close, "No information",
                      modifier = Modifier.size(45.dp))
                Text("No result found for `$searchPhrase` `$selectedCategory`",
                style = karlaTypography.caption)
            }

        }
        else {
            LazyColumn(
                modifier = Modifier.weight(3f, fill = true)
            ) {

                items(
                    items = filterList,
                    itemContent = {
                        menuItemCard(menuItem = it)
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryButton(category:String, isSelected:Boolean = false, onClick: ()->Unit){
    Button(onClick = onClick,
    shape = RoundedCornerShape(20.dp),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = if (isSelected) Primary1 else Secondary3
    )) {
        Text(category, color = if(isSelected) Secondary3 else Primary1,
        style = karlaTypography.body2)
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun menuItemCard(menuItem: MenuItem){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 20.dp)){
            Column(modifier = Modifier.weight(3f)){
                Text(menuItem.title, style = karlaTypography.caption)
                Text(menuItem.description)
                Spacer(modifier = Modifier.height(15.dp))
                Text("$ ${menuItem.price}", style = karlaTypography.body2,
                color = Color.Black, fontSize = 18.sp)
            }
        GlideImage(model =menuItem.image,
            contentDescription = "${menuItem.title} Image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .weight(2.5f)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillWidth)
    }
    Divider(color = Secondary3, thickness = 2.dp)
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}

