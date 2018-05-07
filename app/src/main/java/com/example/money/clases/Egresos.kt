package com.example.money.clases


class Egresos {
    var EngresoId:Int?=null
    var categoriaEgreso:String?=null
    var descripcionEngreso:String?=null
    var montoEngreso:String?=null
    var fechaEngreso:String?=null
    var mesEgreso:String?=null
    var anioEgreso:String?=null

    constructor(EngresoId: Int?, categoriaEgreso: String?, descripcionEngreso: String?, montoEngreso: String?, fechaEngreso: String?, mesEgreso: String?, anioEgreso: String?) {
        this.EngresoId = EngresoId
        this.categoriaEgreso = categoriaEgreso
        this.descripcionEngreso = descripcionEngreso
        this.montoEngreso = montoEngreso
        this.fechaEngreso = fechaEngreso
        this.mesEgreso = mesEgreso
        this.anioEgreso = anioEgreso
    }
}