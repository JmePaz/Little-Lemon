package com.jamesco.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.text.BoringLayout
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jamesco.littlelemon.ui.theme.Primary1
import com.jamesco.littlelemon.ui.theme.Primary2

@Composable
fun Onboarding(navController: NavHostController){

    var firstName  by rememberSaveable {
        mutableStateOf("")
    }

    var lastName by rememberSaveable {
        mutableStateOf("")
    }

    var emailAddress by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = remember{context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)}

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter =  painterResource(id = R.drawable.logo),
            "A logo",
        modifier = Modifier
            .size(width = 200.dp, 100.dp)
            .padding(vertical = 16.dp),
        contentScale = ContentScale.Fit)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            color = Color(0xFF495E57)) {
            Text("Let's get to know you",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
            modifier = Modifier.padding(vertical = 40.dp))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly) {
            Text("Personal Information", fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
            MyTextField("First name",value = firstName, onValueChange ={ firstName = it} )
            MyTextField("Last name",value = lastName, onValueChange = { lastName = it})
            MyTextField("Email",value = emailAddress, onValueChange = { emailAddress = it})
        }


        Button(onClick = {
            if(validateData(firstName, lastName, emailAddress)){
                //store
                val editor =sharedPreferences.edit()
                editor.putString("firstName", firstName)
                editor.putString("lastName", lastName)
                editor.putString("email", emailAddress)
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                //navigation
                navController.navigate(Home.route)

                //successful message
                Toast.makeText(context, "Registration successful.",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Registration unsuccessful. Please enter all data.",
                Toast.LENGTH_SHORT).show()
            }
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Primary2,
                contentColor =  Color.Black
            ), border = BorderStroke(1.dp, color = Color(0xFFFBDABB)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
            Text(text = "Register")
        }
    }
}
fun validateData(firstName:String, lastName:String, emailAddress:String):Boolean{
    if(firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank()){
        return false
    }


    return  true

}

@Composable
fun MyTextField(tag:String,value:String, onValueChange:(String)->Unit, readOnly:Boolean = false){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = tag)
        OutlinedTextField(value = value, onValueChange = onValueChange,
            readOnly = readOnly,
        modifier = Modifier.fillMaxWidth()
            , singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

    }
}



@Preview(showSystemUi = true)
@Composable
fun OnboardPreview(){

    //Onboarding()
}