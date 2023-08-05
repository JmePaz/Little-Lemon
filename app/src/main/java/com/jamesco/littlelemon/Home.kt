package com.jamesco.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jamesco.littlelemon.ui.theme.Primary1
import com.jamesco.littlelemon.ui.theme.karlaTypography
import com.jamesco.littlelemon.ui.theme.marzakiTypography

@Composable
fun Home(navController: NavHostController){
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
            .weight(2.2f)
            ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                Text("Little Lemon", style = marzakiTypography.h1,
                fontSize = 48.sp)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)){
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
                OutlinedTextField(value = "", onValueChange ={},
                leadingIcon = {Icon(Icons.Default.Search,"Search Icon")},
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = {Text("Enter Search Phrase")}
                    )
            }

        }

        Spacer(modifier = Modifier.weight(3f, true))
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}