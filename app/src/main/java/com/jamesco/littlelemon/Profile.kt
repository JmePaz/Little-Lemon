package com.jamesco.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jamesco.littlelemon.ui.theme.Primary1

//@Preview(showSystemUi =  true)

@Composable
fun Profile( navController: NavHostController){
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = remember{context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)}


    var firstName  by rememberSaveable {
        mutableStateOf(sharedPreferences.getString("firstName",""))
    }

    var lastName by rememberSaveable {
        mutableStateOf(sharedPreferences.getString("lastName",""))
    }

    var emailAddress by rememberSaveable {
        mutableStateOf(sharedPreferences.getString("email",""))
    }

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
            .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = {
                navController.navigate(Home.route)
            }, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.ArrowBack, "Go back")
            }
            Image(painter = painterResource(R.drawable.logo),
                "Little Lemon Logo",
                modifier = Modifier
                    .weight(2f, true)
                    .requiredHeightIn(min = 100.dp),
                contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.weight(1f) )

        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly) {
            Text("Personal Information", fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            MyTextField("First name",value = firstName!!, onValueChange ={ } ,true)
            MyTextField("Last name",value = lastName!!, onValueChange = { }, true)
            MyTextField("Email",value = emailAddress!!, onValueChange = { }, true)
        }


        Button(onClick = {
            //reset data
            sharedPreferences.edit().clear().apply()
            //
            Toast.makeText(context, "Logout Successfully.",
                Toast.LENGTH_SHORT).show()
            //logout
            navController.navigate(OnBoarding.route)
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Primary1,
                contentColor =  Color.Black
            ), border = BorderStroke(1.dp, color = Color(0xFFFBDABB)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
            Text(text = "Log Out")
        }
    }
}

@Preview(showSystemUi =  true)
@Composable
fun ProfilePreview(){
    Profile(rememberNavController())
}