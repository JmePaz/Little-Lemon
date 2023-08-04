package com.jamesco.littlelemon

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Profile(){
    Text("This is Profile Page")
}

@Preview(showSystemUi =  true)
@Composable
fun ProfilePreview(){
    Profile()
}