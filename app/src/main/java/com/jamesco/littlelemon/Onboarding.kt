package com.jamesco.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import  androidx.compose.runtime.getValue
import  androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamesco.littlelemon.ui.theme.Primary1

@Composable
fun Onboarding(){

    var firstName  by rememberSaveable {
        mutableStateOf("")
    }

    var lastName by rememberSaveable {
        mutableStateOf("")
    }

    var emailAddress by rememberSaveable {
        mutableStateOf("")
    }

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

        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Primary1,
                contentColor =  Color.Black
            ), border = BorderStroke(1.dp, color = Color(0xFFFBDABB)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
            Text(text = "Register")
        }
    }
}

@Composable
fun MyTextField(tag:String,value:String, onValueChange:(String)->Unit){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = tag)
        OutlinedTextField(value = value, onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth()
            , singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun OnboardPreview(){

    Onboarding()
}