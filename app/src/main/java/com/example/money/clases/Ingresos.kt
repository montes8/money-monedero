package com.example.money.clases

/**
 * Created by thai on 21/04/2018.
 */
class Ingresos {

    var usuIngresoId:Int?=null
    var usuIngreso:String?=null
    var montoIngreso:String?=null
    var fechaIngreso:String?=null
    var mesIngreso:String?=null
    var anioIngreso:String?=null

    constructor(usuIngresoId: Int, usuIngreso: String, montoIngreso: String, fechaIngreso: String, mesIngreso: String, anioIngreso: String) {
        this.usuIngresoId = usuIngresoId
        this.usuIngreso = usuIngreso
        this.montoIngreso = montoIngreso
        this.fechaIngreso = fechaIngreso
        this.mesIngreso = mesIngreso
        this.anioIngreso = anioIngreso
    }
}