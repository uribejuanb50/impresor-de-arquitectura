package src

import java.io.File

class Nodo (val nombre : String, val path : File) {

    val listaSubArchivos: MutableList<Nodo?> = mutableListOf()
    var letrasPalabraMasLarga : Int = 0

    fun crearSubDirectorios() : Nodo {

        if(this.path.isFile){
            return this
        }

        
    }

    //se puede mejorar usando el this
    fun calcularMedidaPalabraMasLarga() : Int{

        println("[Nodo] Lugar actual: ${this.path}")

        if(this.listaSubArchivos.isEmpty()){
            return 0
        }
        if(this.listaSubArchivos.size == 1) {
            return this.validarArchivo()
        }

        var palabraMasGrande : Int = 0

        for(nodo in this.listaSubArchivos){
            if(nodo == null){
                continue
            }
            val mayorSubDirectorio = nodo.calcularMedidaPalabraMasLarga()
            palabraMasGrande = maxOf(mayorSubDirectorio, this.validarArchivo())
        }
        return palabraMasGrande
    }

    fun validarArchivo() : Int{
        if(this.path.isDirectory){
            return 0
        }
        return this.nombre.length
    }
    /*
    fun imprimirParaREADME(nivel : Int, nodo : Nodo?) : String {
        if(nodo == null) {
            return ""
        }
        if(nodo.listaSubArchivos.size == 1){
            return
        }
    }
    */
}




















