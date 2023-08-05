package com.jamesco.littlelemon

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@SerialName("menu data")
@Serializable
data class MenuNetwork( val menu:List<MenuItemNetwork> ){
}

@Serializable
data class MenuItemNetwork(
    val id:Int,
    val title:String,
    val description:String ,
    val price: String,
    val image:String,
    val category: String
){

}