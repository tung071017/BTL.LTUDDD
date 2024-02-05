package com.example.hetcuu.data

import java.io.Serializable

class User : Serializable {

    var name : String = ""
    var email : String = ""
    var password : String = ""
    var followId : String = ""

    constructor()
    constructor(name : String, email: String, password : String)
}