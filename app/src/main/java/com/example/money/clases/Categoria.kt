package com.example.money.clases

class Categoria {

    var categoriaId:Int?=null
    var tituloCategoria:String?=null
    constructor(categoriaId: Int, tituloCategoria: String) {
        this.categoriaId = categoriaId
        this.tituloCategoria = tituloCategoria
    }
    constructor(){

    }
    fun getCategoriaid(): Int? {return categoriaId }
    fun setCategoriaid(categoriaId: Int){this.categoriaId=categoriaId}
    fun getTitulocategoria(): String? {return tituloCategoria}
    fun setTitulocategoria(tituloCategoria: String){this.tituloCategoria=tituloCategoria}
    override fun toString(): String {
        return tituloCategoria!!
    }


}