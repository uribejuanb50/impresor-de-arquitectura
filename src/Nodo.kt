package src

import java.io.File

class Nodo (val nombre : String, val path : File) {

    val listaSubArchivos: MutableList<Nodo> = mutableListOf()
    var letrasPalabraMasLarga : Int = 0

    fun crearSubDirectorios() : Nodo? {

        if(this.path.isFile){
            return this
        }

        val directorio = this.path.listFiles()?: return null

        for(direccion in directorio){

            val nodo : Nodo = Nodo(direccion.name, direccion)
            val nodoActualizado = nodo.crearSubDirectorios()

            if(nodoActualizado != null)
                this.listaSubArchivos.add(nodoActualizado)
        }

        return this
    }

    //se puede mejorar usando el this
    fun calcularMedidaPalabraMasLarga() : Int{

        if(this.path.isFile) {
            return this.nombre.length
        }
        if(this.listaSubArchivos.isEmpty()){
            return 0
        }

        var palabraMasGrande : Int = 0

        for(nodo in this.listaSubArchivos){

            val mayorSubDirectorio = nodo.calcularMedidaPalabraMasLarga()

            palabraMasGrande = maxOf(mayorSubDirectorio, this.nombre.length, palabraMasGrande)
        }
        return palabraMasGrande
    }

    fun calcularProfundidad() : Int{
        if(this.path.isFile){
            return 1
        }
        if(this.listaSubArchivos.isEmpty()){
            return 0
        }

        var nivelMasAlto : Int = 0

        for(nodo in this.listaSubArchivos){

            val sumatoria = nodo.calcularProfundidad() + 1
            nivelMasAlto = maxOf(sumatoria, nivelMasAlto)
        }

        return nivelMasAlto
    }

    fun imprimirParaREADMEsencillo(md : ArrayList<String>, nivel : Int = 0) : String {

        if(this.path.isFile){
            println("[con1] Nivel: $nivel ")
            return this.nombre + "\n"
        }
        if(this.listaSubArchivos.isEmpty()){
            println("[con2] Nivel: $nivel ")
            return ""
        }

        var arquitectura : String = this.nombre +"/\n"
        var mdreadme = md

        for((indice, subdirectorio) in this.listaSubArchivos.withIndex()){

            //estamos usando memoria dinamica, primero toca reasignar el valor usando la función
            if(indice == this.listaSubArchivos.lastIndex){
                arquitectura += if(nivel == 0) "" else mdreadme[nivel - 1]
                arquitectura += "└── " + subdirectorio.imprimirParaREADMEsencillo(mdreadme, nivel + 1)

                if(nivel == 0) mdreadme[nivel] = "    " else mdreadme[nivel] = mdreadme[nivel - 1] + "    "

                //println("[if1] Nivel: $nivel | arq: $arquitectura")
            }
            else {
                if (nivel == 0) {
                    mdreadme[nivel] = "│   "
                    arquitectura += "├── " + this.nombre + "/\n"
                    //el nivel se coloca en 1 porque ya llega de por si en 0, es redundante colocar nivel + 1 o 1 porque da lo mismo
                    arquitectura += mdreadme[nivel] + subdirectorio.imprimirParaREADMEsencillo(mdreadme, 1)//no preguntes solo interiorizalo
                    //println("[if2] Nivel: $nivel | arq: $arquitectura")
                } else {
                    mdreadme[nivel] = mdreadme[nivel - 1] + "│   "
                    arquitectura += mdreadme[nivel] + subdirectorio.imprimirParaREADMEsencillo(mdreadme, nivel + 1)
                    //println("[if3] Nivel: $nivel | arq: $arquitectura")
                }
            }

            mdreadme = ArrayList(List(md.size) {""})
        }

        return arquitectura
    }

    init{
        println("[Nodo] nombre: ${this.nombre}, path: ${this.path}")
    }
}




















