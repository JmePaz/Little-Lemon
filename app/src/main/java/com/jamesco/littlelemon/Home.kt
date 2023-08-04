package com.jamesco.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.15f),
            horizontalArrangement = Arrangement.SpaceAround,
             verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(R.drawable.logo),
                "Little Lemon Logo",
            modifier = Modifier.size(200.dp, 45.dp),
            contentScale = ContentScale.FillHeight)
            Button(onClick = {
                navController.navigate(Profile.route)
            }) {
                Text("Profile")
            }
        }


    }
}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}