package com.example.money.clases

/**
 * Created by thai on 11/04/2018.
 */
class User {
    var usuId:Int?=null
    var usuName:String?=null
    var usuario:String?=null
    var password:String?=null

    constructor(usuId: Int, usuName: String, usuario: String, password: String) {
        this.usuId = usuId
        this.usuName = usuName
        this.usuario = usuario
        this.password = password
    }
}