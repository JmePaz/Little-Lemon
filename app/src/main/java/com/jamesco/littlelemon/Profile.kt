package com.jamesco.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
        Image(painter =  painterResource(id = R.drawable.logo),
            "A logo",
            modifier = Modifier
                .size(width = 200.dp, 100.dp)
                .padding(vertical = 16.dp),
            contentScale = ContentScale.Fit)
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

//@Preview(showSystemUi =  true)
//@Composable
//fun ProfilePreview(){
//    Profile()
//}