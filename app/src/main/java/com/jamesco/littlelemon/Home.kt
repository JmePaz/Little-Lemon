package com.jamesco.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
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
            modifier = Modifier.weight(1f)
                .size(65.dp)
                .clickable {
                   //go to profile
                   navController.navigate(Profile.route)

                },
            contentScale = ContentScale.Fit)
        }


    }
}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}