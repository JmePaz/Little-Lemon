package com.jamesco.littlelemon

interface Destinations{
    val name:String
    val route:String
}

object Home: Destinations{
    override val name: String = "Home"
    override val route: String = "Home"
}

object Profile:Destinations{
    override val name: String = "Profile"
    override val route: String = "Profile"
}

object OnBoarding:Destinations{
    override val name: String = "OnBoarding"
    override val route: String = "OnBoarding"
}