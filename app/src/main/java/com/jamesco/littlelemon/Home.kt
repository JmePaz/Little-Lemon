package com.jamesco.littlelemon

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home(){
    Text("This is Home page")
}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home()
}