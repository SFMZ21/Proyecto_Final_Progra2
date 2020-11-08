package com.example.proyectofinalprogra2

class Model {

    lateinit var tag: String
    lateinit var descripcion: String
    lateinit var monto : String
    lateinit var fecha : String
    lateinit var hora : String

    constructor(tag : String, descripcion : String, monto : String, fecha : String, hora : String){
        this.tag = tag
        this.descripcion = descripcion
        this.monto = monto
        this.fecha = fecha
        this.hora = hora
    }

}