package com.example.desafioarquitectura.ui.theme

class Results {

    val id : Int = 0
    val name : String = ""
    val status : String? = null
    val species : String? = null
    val type : String? = null
    val gender : String? = null
    val origin : Origin? = Origin()
    val location : Location? = Location()
    val image : String = ""
    val episode: ArrayList<String> = arrayListOf()
    val url : String? = null
    val created : String? = null
}

class Origin{
    val name : String? = null
    val url : String? = null
}

class Location{
    val name : String? = null
    val url : String? = null
}