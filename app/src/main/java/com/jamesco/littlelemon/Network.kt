package com.jamesco.littlelemon

import android.view.Menu
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
    fun toMenuItem():MenuItem{
        return  MenuItem(
            this.id, this.title,
            this.description,
            this.price,
            this.image,
            this.category
        )
    }
}